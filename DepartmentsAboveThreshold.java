import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept,
    int salary
){}

public class DepartmentsAboveThreshold {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex","IT",90000),
                new Employee("Sam","IT",85000),
                new Employee("John","HR",50000),
                new Employee("Emma","HR",55000),
                new Employee("Mike","Sales",95000)
            );

        employees.stream()
                 .collect(
                     Collectors.groupingBy(
                         Employee::dept,
                         Collectors.averagingInt(
                             Employee::salary
                         )
                     )
                 )
                 .entrySet()
                 .stream()
                 .filter(
                     e -> e.getValue()>80000
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
