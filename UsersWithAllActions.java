import java.util.*;
import java.util.stream.*;

record Log(
    String user,
    String action
){}

public class UsersWithAllActions {

    public static void main(String[] args){

        List<Log> logs=
            List.of(
                new Log("Alex","LOGIN"),
                new Log("Alex","UPLOAD"),
                new Log("Alex","DOWNLOAD"),
                new Log("Sam","LOGIN"),
                new Log("Sam","UPLOAD")
            );

        Set<String> allActions=
            logs.stream()
                .map(Log::action)
                .collect(Collectors.toSet());

        logs.stream()
            .collect(
                Collectors.groupingBy(
                    Log::user,
                    Collectors.mapping(
                        Log::action,
                        Collectors.toSet()
                    )
                )
            )
            .entrySet()
            .stream()
            .filter(
                e->e.getValue()
                     .containsAll(allActions)
            )
            .map(Map.Entry::getKey)
            .forEach(
                System.out::println
            );
    }
}
