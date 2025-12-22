import java.nio.file.*;

public class ConfigLoader {
    static String get(String key) throws Exception {
        String env = System.getenv(key);
        if (env != null) return env;

        for (String l : Files.readAllLines(Path.of("config.env"))) {
            String[] p = l.split("=");
            if (p[0].equals(key)) return p[1];
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(get("DB_HOST"));
    }
}
