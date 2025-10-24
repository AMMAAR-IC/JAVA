// JsonSchemaValidate.java
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.nio.file.*;

public class JsonSchemaValidate {
    public static void main(String[] args) throws Exception {
        JSONObject rawSchema = new JSONObject(new JSONTokener(Files.newInputStream(Path.of("schema.json"))));
        Schema schema = SchemaLoader.load(rawSchema);

        JSONObject data = new JSONObject(new JSONTokener(Files.newInputStream(Path.of("data.json"))));
        schema.validate(data); // throws ValidationException if invalid
        System.out.println("JSON is valid according to schema.");
    }
}
