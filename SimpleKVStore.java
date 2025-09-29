// SimpleKVStore.java
import java.io.*;
import java.util.*;

public class SimpleKVStore {
    static File file = new File("db.kv");

    public static void put(String key, String value) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(key + "=" + value); bw.newLine();
        }
    }

    public static String get(String key) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(key + "=")) return line.split("=", 2)[1];
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        put("username", "ammar");
        put("language", "java");
        System.out.println(get("username"));
    }
}
