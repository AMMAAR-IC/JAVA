import java.util.*;

public class AsciiSum {

    public static void main(String[] args){

        List<String> words=
            List.of("A","Java","AI");

        words.stream()
             .map(s->
                 s.chars().sum()
             )
             .forEach(System.out::println);
    }
}
