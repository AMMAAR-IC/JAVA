import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.Instant;
import java.util.*;

/**
 * TinyKV - Minimal persistent key-value store (append-only log) with TTL.
 *
 *  Usage:
 *      javac TinyKV.java
 *      java TinyKV data.kv
 *
 *  Commands:
 *      PUT <key> "<value with spaces>" [ttlSec]
 *      GET <key>
 *      DEL <key>
 *      KEYS
 *      COMPACT
 *      EXIT
 */
public class TinyKV {

    private static final String MAGIC = "TINYKVv1";
    private final Path logFile;
    private final Map<String, Entry> map = new HashMap<>();
    private final Deque<Long> offsets = new ArrayDeque<>(); // offsets for compaction heuristic

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java TinyKV <data-file>");
            System.exit(1);
        }
        Path file = Paths.get(args[0]);
        TinyKV kv = new TinyKV(file);
        kv.load();
        kv.repl();
    }

    record Entry(String value, long expireAt) {}

    TinyKV(Path logFile) { this.logFile = logFile; }

    /* ============================ Persistence ============================ */

    private void load() throws IOException {
        if (!Files.exists(logFile)) {
            Files.writeString(logFile, MAGIC + "\n", StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return;
        }
        try (BufferedReader br = Files.newBufferedReader(logFile, StandardCharsets.UTF_8)) {
            String first = br.readLine();
            if (first == null || !first.startsWith(MAGIC)) {
                throw new IOException("Invalid store header");
            }
            String line; long offset = first.length() + 1;
            while ((line = br.readLine()) != null) {
                applyLogLine(line);
                offset += line.length() + 1;
                offsets.add(offset);
                if (offsets.size() > 1000) offsets.removeFirst();
            }
        }
        purgeExpired();
    }

    private synchronized void append(String line) throws IOException {
        Files.writeString(logFile, line + "\n", StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        offsets.add(offsets.isEmpty() ? (long)(MAGIC.length()+1+line.length()+1)
                : offsets.getLast() + line.length() + 1);
        if (offsets.size() > 1000) offsets.removeFirst();
    }

    private void applyLogLine(String line) {
        if (line.startsWith("PUT ")) {
            // PUT key base64(ttl:epochMillis or -) base64(value)
            String[] parts = line.split(" ", 4);
            if (parts.length < 4) return;
            String key = parts[1];
            long expireAt = Long.parseLong(new String(Base64.getDecoder().decode(parts[2]), StandardCharsets.UTF_8));
            String val = new String(Base64.getDecoder().decode(parts[3]), StandardCharsets.UTF_8);
            map.put(key, new Entry(val, expireAt));
        } else if (line.startsWith("DEL ")) {
            String key = line.substring(4);
            map.remove(key);
        }
    }

    private void purgeExpired() {
        long now = System.currentTimeMillis();
        map.entrySet().removeIf(e -> e.getValue().expireAt > 0 && e.getValue().expireAt <= now);
    }

    /* ============================ REPL ============================ */

    private void repl() throws IOException {
        System.out.println("TinyKV ready. Type HELP for commands.");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("kv> ");
                if (!sc.hasNextLine()) break;
                String line = sc.nextLine().trim();
                if (line.isBlank()) continue;

                String op = head(line);
                switch (op) {
                    case "HELP" -> System.out.println("""
                        PUT <key> "<value with spaces>" [ttlSec]
                        GET <key>
                        DEL <key>
                        KEYS
                        COMPACT
                        EXIT
                        """);
                    case "EXIT", "QUIT" -> { return; }
                    case "PUT" -> handlePut(line);
                    case "GET" -> handleGet(line);
                    case "DEL" -> handleDel(line);
                    case "KEYS" -> handleKeys();
                    case "COMPACT" -> handleCompact();
                    default -> System.out.println("Unknown command. Type HELP.");
                }
            }
        }
    }

    private static String head(String line) {
        int i = line.indexOf(' ');
        return (i < 0 ? line : line.substring(0, i)).toUpperCase(Locale.ROOT);
    }

    private void handlePut(String line) throws IOException {
        // PUT <key> "<value>" [ttlSec]
        // naive parser for quoted value
        String rest = line.substring(3).trim();
        int sp = rest.indexOf(' ');
        if (sp < 0) { System.out.println("Usage: PUT <key> \"<value>\" [ttlSec]"); return; }
        String key = rest.substring(0, sp).trim();
        String tail = rest.substring(sp).trim();
        if (!tail.startsWith("\"")) { System.out.println("Value must be in quotes."); return; }
        int endq = findClosingQuote(tail, 0);
        if (endq < 0) { System.out.println("Unclosed quotes."); return; }
        String value = unescape(tail.substring(1, endq));
        String after = tail.substring(endq + 1).trim();
        long ttlSec = 0;
        if (!after.isEmpty()) {
            try { ttlSec = Long.parseLong(after); } catch (NumberFormatException ignored) {}
        }
        long expireAt = ttlSec > 0 ? System.currentTimeMillis() + ttlSec * 1000 : -1;
        String encodedTTL = Base64.getEncoder().encodeToString(Long.toString(expireAt).getBytes(StandardCharsets.UTF_8));
        String encodedVal = Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));

        append("PUT " + key + " " + encodedTTL + " " + encodedVal);
        map.put(key, new Entry(value, expireAt));
        System.out.printf("OK (key=%s%s)%n", key, ttlSec > 0 ? ", ttl="+ttlSec+"s" : "");
    }

    private void handleGet(String line) {
        String[] parts = line.split("\\s+", 2);
        if (parts.length != 2) { System.out.println("Usage: GET <key>"); return; }
        purgeExpired();
        Entry e = map.get(parts[1]);
        if (e == null) { System.out.println("(nil)"); return; }
        if (e.expireAt > 0 && e.expireAt <= System.currentTimeMillis()) { System.out.println("(expired)"); return; }
        System.out.println(e.value);
    }

    private void handleDel(String line) throws IOException {
        String[] parts = line.split("\\s+", 2);
        if (parts.length != 2) { System.out.println("Usage: DEL <key>"); return; }
        String key = parts[1];
        if (map.remove(key) != null) {
            append("DEL " + key);
            System.out.println("OK");
        } else {
            System.out.println("(no such key)");
        }
    }

    private void handleKeys() {
        purgeExpired();
        if (map.isEmpty()) { System.out.println("(empty)"); return; }
        map.keySet().stream().sorted().forEach(System.out::println);
    }

    private void handleCompact() throws IOException {
        purgeExpired();
        Path tmp = Paths.get(logFile.toString() + ".tmp");
        try (BufferedWriter bw = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            bw.write(MAGIC); bw.newLine();
            for (Map.Entry<String, Entry> e : map.entrySet()) {
                String ttl = Base64.getEncoder().encodeToString(Long.toString(e.getValue().expireAt).getBytes(StandardCharsets.UTF_8));
                String val = Base64.getEncoder().encodeToString(e.getValue().value.getBytes(StandardCharsets.UTF_8));
                bw.write("PUT " + e.getKey() + " " + ttl + " " + val);
                bw.newLine();
            }
        }
        Files.move(tmp, logFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        offsets.clear();
        System.out.println("Compacted.");
    }

    private static int findClosingQuote(String s, int start) {
        boolean esc = false;
        for (int i = start + 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (esc) { esc = false; continue; }
            if (c == '\\') { esc = true; continue; }
            if (c == '"') return i;
        }
        return -1;
    }

    private static String unescape(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        boolean esc = false;
        for (char c : s.toCharArray()) {
            if (esc) {
                sb.append(switch (c) {
                    case 'n' -> '\n';
                    case 'r' -> '\r';
                    case 't' -> '\t';
                    case '"' -> '"';
                    case '\\' -> '\\';
                    default -> c;
                });
                esc = false;
            } else if (c == '\\') {
                esc = true;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
