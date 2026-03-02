import java.util.*;

public class InvertedIndex {
    static Map<String,List<String>> index=new HashMap<>();

    static void add(String doc,String text){
        for(String w:text.split(" "))
            index.computeIfAbsent(w,k->new ArrayList<>()).add(doc);
    }

    public static void main(String[] args){
        add("doc1","java systems");
        add("doc2","java distributed");

        System.out.println(index);
    }
}
