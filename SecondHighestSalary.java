import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept,
    int salary
){}

public class SecondHighestSalary {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex","IT",90000),
                new Employee("Sam","IT",80000),
                new Employee("John","IT",70000),
                new Employee("Emma","HR",95000),
                new Employee("Mike","HR",75000)
            );

        Map<String,Optional<Employee>> result=

            employees.stream()
                     .collect(
                         Collectors.groupingBy(
                             Employee::dept,
                             Collectors.collectingAndThen(
                                 Collectors.toList(),
                                 list ->
                                     list.stream()
                                         .sorted(
                                             Comparator
                                             .comparingInt(
                                                 Employee::salary
                                             )
                                             .reversed()
                                         )
                                         .skip(1)
                                         .findFirst()
                             )
                         )
                     );

        System.out.println(result);
    }
}
