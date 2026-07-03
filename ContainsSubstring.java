import java.util.*;

public class ContainsSubstring {

    public static void main(String[] args){

        List<String> words=
            List.of("java","javascript",
                    "python","javafx");

        words.stream()
             .filter(w->w.contains("java"))
             .forEach(System.out::println);
    }
}
