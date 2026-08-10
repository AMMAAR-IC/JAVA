import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    int salary
){}

public class SameSalaryEmployees {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex",70000),
                new Employee("Sam",70000),
                new Employee("Emma",90000),
                new Employee("John",60000)
            );

        employees.stream()
                 .collect(
                     Collectors.groupingBy(
                         Employee::salary
                     )
                 )
                 .values()
                 .stream()
                 .filter(
                     list->list.size()>1
                 )
                 .forEach(System.out::println);
    }
}
