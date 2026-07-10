import java.util.*;
import java.util.stream.*;

public class LongestPerGroup {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "AI",
                "Java",
                "Code",
                "Backend",
                "Microservice"
            );

        Map<Integer,String> map=
            words.stream()
                 .collect(
                     Collectors.toMap(
                         String::length,
                         s->s,
                         (a,b)->a.length()>b.length()
                                 ?a:b
                     )
                 );

        System.out.println(map);
    }
}
