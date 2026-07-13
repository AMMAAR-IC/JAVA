import java.util.*;

record Department(
    String name,
    List<String> employees
){}

public class FlattenDepartments {

    public static void main(String[] args){

        List<Department> departments=
            List.of(
                new Department(
                    "IT",
                    List.of(
                        "Alex",
                        "Sam"
                    )
                ),
                new Department(
                    "HR",
                    List.of(
                        "John",
                        "Emma"
                    )
                )
            );

        departments.stream()
                   .flatMap(
                       d->
                       d.employees()
                        .stream()
                   )
                   .forEach(
                       System.out::println
                   );
    }
}
