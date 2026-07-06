import java.util.*;
import java.util.stream.*;

public class PartitionByLength {

    public static void main(String[] args){

        List<String> words=
            List.of("AI",
                    "Java",
                    "Programming",
                    "Code");

        Map<Boolean,List<String>> map=
            words.stream()
                 .collect(
                     Collectors.partitioningBy(
                         s->s.length()>4
                     )
                 );

        System.out.println(map);
    }
}
