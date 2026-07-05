import java.util.*;

public class NoNullElements {

    public static void main(String[] args){

        List<String> words=
            Arrays.asList(
                "java","stream","api"
            );

        boolean result=
            words.stream()
                 .noneMatch(
                     Objects::isNull
                 );

        System.out.println(result);
    }
}
