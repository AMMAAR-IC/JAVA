// CSVDedupe.java
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class CSVDedupe {
    public static void main(String[] args) throws Exception {
        Path in = Path.of("data.csv"), out = Path.of("data_dedup.csv");
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        Set<String> seen = new HashSet<>();
        try (BufferedReader br = Files.newBufferedReader(in);
             BufferedWriter bw = Files.newBufferedWriter(out)) {
            String line;
            while ((line = br.readLine()) != null) {
                md.update(line.getBytes());
                String h = Base64.getEncoder().encodeToString(md.digest());
                if (!seen.contains(h)) { seen.add(h); bw.write(line); bw.newLine(); }
            }
        }
        System.out.println("Wrote deduped CSV.");
    }
}
