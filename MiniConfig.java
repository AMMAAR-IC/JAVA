import java.io.*;
import java.util.*;

public class MiniConfig {
    private final Properties props = new Properties();

    public MiniConfig(String file) throws IOException {
        try (FileReader fr = new FileReader(file)) { props.load(fr); }
    }

    public String get(String key) { return props.getProperty(key); }

    public static void main(String[] args) throws Exception {
        MiniConfig cfg = new MiniConfig("config.ini");
        System.out.println("DB = " + cfg.get("db"));
    }
}
