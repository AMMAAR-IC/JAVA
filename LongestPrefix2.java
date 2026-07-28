import java.util.*;

public class LongestPrefix {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "stream",
                "street",
                "stress"
            );

        String prefix=words.get(0);

        while(
            words.stream()
                 .anyMatch(
                     s->!s.startsWith(prefix)
                 )
        ){
            prefix=
                prefix.substring(
                    0,
                    prefix.length()-1
                );
        }

        System.out.println(prefix);
    }
}
