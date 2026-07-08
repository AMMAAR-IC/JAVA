import java.util.*;
import java.util.stream.*;

public class JoinHyphen {

    public static void main(String[] args){

        List<String> words=
            List.of(
                "Java",
                "Stream",
                "API"
            );

        String result=
            words.stream()
                 .collect(
                     Collectors.joining("-")
                 );

        System.out.println(result);
    }
}
