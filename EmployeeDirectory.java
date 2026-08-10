import java.util.*;
import java.util.stream.*;

record Employee(
    int id,
    String name
){}

public class EmployeeDirectory {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee(101,"Alex"),
                new Employee(102,"Sam"),
                new Employee(103,"Emma")
            );

        Map<Integer,String> directory=

            employees.stream()
                     .collect(
                         Collectors.toMap(
                             Employee::id,
                             Employee::name
                         )
                     );

        System.out.println(directory);
    }
}
