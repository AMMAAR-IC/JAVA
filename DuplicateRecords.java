import java.util.*;
import java.util.function.*;
import java.util.stream.*;

record User(
    String email
){}

public class DuplicateRecords {

    public static void main(String[] args){

        List<User> users=
            List.of(
                new User("a@test.com"),
                new User("b@test.com"),
                new User("a@test.com")
            );

        users.stream()
             .collect(
                 Collectors.groupingBy(
                     User::email,
                     Collectors.counting()
                 )
             )
             .entrySet()
             .stream()
             .filter(
                 e->e.getValue()>1
             )
             .forEach(
                 System.out::println
             );
    }
}
