import java.util.*;

public class EndsWithE {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "apple",
                "orange",
                "java",
                "code"
            );

        long count=
            words.stream()
                 .filter(
                     s->s.endsWith("e")
                 )
                 .count();

        System.out.println(count);
    }
}
