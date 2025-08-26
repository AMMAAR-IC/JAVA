import java.util.*;

public class MiniJSON {
    public static Map<String, String> parse(String json) {
        Map<String, String> map = new HashMap<>();
        json = json.trim().replaceAll("[{}\"]", "");
        for (String pair : json.split(",")) {
            String[] kv = pair.split(":");
            map.put(kv[0].trim(), kv[1].trim());
        }
        return map;
    }

    public static void main(String[] args) {
        String json = "{\"name\":\"Ammaar\", \"role\":\"Batman\"}";
        Map<String, String> obj = parse(json);
        System.out.println(obj.get("name"));
    }
}
