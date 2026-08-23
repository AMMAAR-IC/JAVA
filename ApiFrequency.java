import java.util.*;
import java.util.function.*;
import java.util.stream.*;

record Request(
    String endpoint,
    int status
){}

public class ApiFrequency {

    public static void main(String[] args){

        List<Request> requests=
            List.of(
                new Request("/users",200),
                new Request("/login",200),
                new Request("/users",404),
                new Request("/users",200),
                new Request("/orders",200)
            );

        requests.stream()
                .collect(
                    Collectors.groupingBy(
                        Request::endpoint,
                        Collectors.counting()
                    )
                )
                .entrySet()
                .stream()
                .sorted(
                    Map.Entry
                    .<String,Long>
                    comparingByValue()
                    .reversed()
                )
                .forEach(
                    System.out::println
                );
    }
}
