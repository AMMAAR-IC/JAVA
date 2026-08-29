import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept,
    int salary
){}

public class ThirdHighestPerDepartment {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("A","IT",90000),
                new Employee("B","IT",80000),
                new Employee("C","IT",70000),
                new Employee("D","IT",60000),

                new Employee("E","HR",95000),
                new Employee("F","HR",85000),
                new Employee("G","HR",75000)
            );

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
                                     .skip(2)
                                     .findFirst()
                         )
                     )
                 )
                 .forEach(
                     (dept,employee) ->
                         System.out.println(
                             dept+" -> "+employee
                         )
                 );
    }
}
