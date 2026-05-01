import java.util.*;
import java.util.stream.*;

public class JoinStream {

    public static void main(String[] args){

        List<String> list=List.of("Java","is","powerful");

        String result = list.stream()
                            .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
