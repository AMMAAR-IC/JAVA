import java.util.*;

public class MiniSQL {
    static List<Map<String,String>> table = new ArrayList<>();

    public static void main(String[] args){
        table.add(Map.of("name","Ammaar","lang","Java"));
        table.add(Map.of("name","Alex","lang","Python"));

        query("SELECT name WHERE lang=Java");
    }

    static void query(String q){
        String cond = q.split("WHERE ")[1];
        String[] kv = cond.split("=");

        for(var row:table)
            if(row.get(kv[0]).equals(kv[1]))
                System.out.println(row);
    }
}
