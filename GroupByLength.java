import java.util.*;
import java.util.stream.*;

public class GroupByLength {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "AI",
                "Java",
                "Code",
                "Programming"
            );

        Map<Integer,List<String>> map=
            words.stream()
                 .collect(
                     Collectors.groupingBy(
                         String::length
                     )
                 );

        System.out.println(map);
    }
}
