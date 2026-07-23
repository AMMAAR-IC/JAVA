import java.util.stream.*;

public class ConsecutiveDuplicates {

    public static void main(String[] args){

        String[] words={
            "java",
            "java",
            "stream",
            "api",
            "api",
            "api"
        };

        IntStream.range(
                    1,
                    words.length
                 )
                 .filter(
                     i->
                     words[i].equals(
                         words[i-1]
                     )
                 )
                 .mapToObj(
                     i->words[i]
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
