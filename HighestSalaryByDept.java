import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept,
    int salary
){}

public class HighestSalaryByDept {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex","IT",70000),
                new Employee("Sam","IT",90000),
                new Employee("John","HR",60000),
                new Employee("Emma","HR",80000)
            );

        Map<String,Optional<Employee>> result=

            employees.stream()
                     .collect(
                         Collectors.groupingBy(
                             Employee::dept,
                             Collectors.maxBy(
                                 Comparator.comparingInt(
                                     Employee::salary
                                 )
                             )
                         )
                     );

        System.out.println(result);
    }
}
