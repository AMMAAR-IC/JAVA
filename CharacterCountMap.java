import java.util.*;
import java.util.stream.*;

public class CharacterCountMap {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "AI",
                "Java",
                "Stream"
            );

        Map<String,Integer> map=
            words.stream()
                 .collect(
                     Collectors.toMap(
                         s->s,
                         String::length
                     )
                 );

        System.out.println(map);
    }
}
