import java.util.*;

public class SimpleHashTable {

    static Map<String,String> map=new HashMap<>();

    public static void main(String[] args){

        map.put("name","Ammaar");
        map.put("lang","Java");

        System.out.println(map.get("name"));
    }
}
