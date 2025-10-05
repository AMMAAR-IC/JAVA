// JsonToCsv.java
import org.json.*;
import java.nio.file.*;

public class JsonToCsv {
    public static void main(String[] args) throws Exception {
        String jsonStr = Files.readString(Path.of("data.json"));
        JSONArray arr = new JSONArray(jsonStr);

        StringBuilder csv = new StringBuilder("id,name,age\n");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            csv.append(obj.getInt("id"))
               .append(",")
               .append(obj.getString("name"))
               .append(",")
               .append(obj.getInt("age"))
               .append("\n");
        }
        Files.writeString(Path.of("data.csv"), csv.toString());
        System.out.println("Converted to data.csv");
    }
}
