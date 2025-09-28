// JsonSchemaLight.java
import com.google.gson.*;
import java.nio.file.*;
import java.util.*;

public class JsonSchemaLight {
    public static boolean validate(String json, List<String> required) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        for (String k : required) if (!obj.has(k)) return false;
        return true;
    }

    public static void main(String[] args) throws Exception {
        String json = Files.readString(Paths.get("sample.json"));
        List<String> req = Arrays.asList("id","name","email");
        System.out.println("Valid: " + validate(json, req));
    }
}
