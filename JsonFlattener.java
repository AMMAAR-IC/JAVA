import org.json.*;
import java.nio.file.*;
import java.util.*;

public class JsonFlattener {
    public static void main(String[] args) throws Exception {
        JSONObject obj = new JSONObject(Files.readString(Path.of("nested.json")));
        Map<String,Object> flat = new LinkedHashMap<>();
        flatten("", obj, flat);
        flat.forEach((k,v)-> System.out.println(k+"="+v));
    }

    static void flatten(String prefix, Object val, Map<String,Object> out){
        if(val instanceof JSONObject jo)
            for(String k: jo.keySet()) flatten(prefix.isEmpty()?k:prefix+"."+k, jo.get(k), out);
        else if(val instanceof JSONArray arr)
            for(int i=0;i<arr.length();i++) flatten(prefix+"["+i+"]", arr.get(i), out);
        else out.put(prefix, val);
    }
}
