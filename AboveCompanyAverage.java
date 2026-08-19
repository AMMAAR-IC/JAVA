import java.util.*;

record Employee(
    String name,
    int salary
){}

public class AboveCompanyAverage {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex",90000),
                new Employee("Sam",60000),
                new Employee("John",70000),
                new Employee("Emma",100000)
            );

        double average=
            employees.stream()
                     .mapToInt(
                         Employee::salary
                     )
                     .average()
                     .orElse(0);

        employees.stream()
                 .filter(
                     e -> e.salary()>average
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
