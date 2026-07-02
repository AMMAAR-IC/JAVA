import java.util.*;

public class TotalCharacters {

    public static void main(String[] args){

        List<String> words=
            List.of("java","stream","api");

        int total=
            words.stream()
                 .mapToInt(String::length)
                 .sum();

        System.out.println(total);
    }
}
