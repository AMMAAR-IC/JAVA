import java.util.*;

public class MaxLengthString {

    public static void main(String[] args){

        List<String> words=
            List.of("java","stream",
                    "microservices","api");

        String result=
            words.stream()
                 .max(
                     Comparator.comparingInt(
                         String::length
                     )
                 )
                 .orElse("");

        System.out.println(result);
    }
}
