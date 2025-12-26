import java.nio.file.*;
import java.util.*;

public class GeoIP {
    public static void main(String[] args) throws Exception {
        Map<String,String> map=new HashMap<>();
        for(String l:Files.readAllLines(Path.of("geo.csv"))){
            var p=l.split(",");
            map.put(p[0],p[1]); // ipPrefix,country
        }
        System.out.println(map.getOrDefault("192.168.","Unknown"));
    }
}
