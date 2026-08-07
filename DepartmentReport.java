import java.util.*;
import java.util.stream.*;

record Employee(
    String department,
    String name
){}

public class DepartmentReport {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("IT","Alex"),
                new Employee("IT","Sam"),
                new Employee("HR","Emma"),
                new Employee("HR","John")
            );

        employees.stream()
                 .collect(
                     Collectors.groupingBy(
                         Employee::department,
                         Collectors.mapping(
                             Employee::name,
                             Collectors.joining(", ")
                         )
                     )
                 )
                 .forEach(System.out::println);
    }
}
