import java.util.*;

public class ReverseStrings {

    public static void main(String[] args){

        List<String> words=
            List.of("java","stream","api");

        words.stream()
             .map(s->
                 new StringBuilder(s)
                     .reverse()
                     .toString()
             )
             .forEach(System.out::println);
    }
}
