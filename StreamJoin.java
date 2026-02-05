import java.util.*;

public class StreamJoin {
    public static void main(String[] args){
        Map<Integer,String> a=Map.of(1,"A",2,"B");
        Map<Integer,String> b=Map.of(2,"X",3,"Y");

        a.keySet().stream()
            .filter(b::containsKey)
            .forEach(k->System.out.println(a.get(k)+"-"+b.get(k)));
    }
}
