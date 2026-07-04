import java.util.*;
import java.util.stream.*;

public class GroupByLastCharacter {

    public static void main(String[] args){

        List<String> words=
            List.of("java","banana",
                    "apple","grape");

        Map<Character,List<String>> map=
            words.stream()
                 .collect(
                     Collectors.groupingBy(
                         w->w.charAt(
                             w.length()-1
                         )
                     )
                 );

        System.out.println(map);
    }
}
