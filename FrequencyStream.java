import java.util.*;
import java.util.stream.*;

public class FrequencyStream {

    public static void main(String[] args){

        List<String> list=List.of("a","b","a","c","b","a");

        Map<String,Long> map =
            list.stream()
                .collect(Collectors.groupingBy(
                    s->s,
                    Collectors.counting()
                ));

        System.out.println(map);
    }
}
