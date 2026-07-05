import java.util.*;

public class SecondLongestString {

    public static void main(String[] args){

        List<String> words=
            List.of("java",
                    "microservice",
                    "stream",
                    "collection");

        String result=
            words.stream()
                 .distinct()
                 .sorted(
                     Comparator.comparingInt(
                         String::length
                     ).reversed()
                 )
                 .skip(1)
                 .findFirst()
                 .orElse("");

        System.out.println(result);
    }
}
