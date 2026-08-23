import java.util.*;
import java.util.function.*;
import java.util.stream.*;

record Log(
    String user,
    String action
){}

public class MostActiveUser {

    public static void main(String[] args){

        List<Log> logs=
            List.of(
                new Log("Alex","LOGIN"),
                new Log("Sam","LOGIN"),
                new Log("Alex","UPLOAD"),
                new Log("Alex","DOWNLOAD"),
                new Log("Sam","UPLOAD")
            );

        logs.stream()
            .collect(
                Collectors.groupingBy(
                    Log::user,
                    Collectors.counting()
                )
            )
            .entrySet()
            .stream()
            .max(
                Map.Entry.comparingByValue()
            )
            .ifPresent(
                System.out::println
            );
    }
}
