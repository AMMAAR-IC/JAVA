// SparkKV.java
import static spark.Spark.*;
import com.google.gson.*;
import java.util.concurrent.ConcurrentHashMap;

public class SparkKV {
    public static void main(String[] args) {
        port(4567);
        ConcurrentHashMap<String,String> db = new ConcurrentHashMap<>();
        Gson gson = new Gson();

        put("/kv/:key", (req, res) -> {
            String key = req.params("key");
            db.put(key, req.body());
            return gson.toJson(Map.of("status","ok","key",key));
        });

        get("/kv/:key", (req, res) -> {
            String key = req.params("key");
            String v = db.get(key);
            if (v==null) { res.status(404); return "Not found"; }
            res.type("application/json"); return v;
        });

        delete("/kv/:key", (req, res) -> {
            db.remove(req.params("key"));
            return "deleted";
        });

        get("/", (q,r) -> "SparkKV running");
    }
}
