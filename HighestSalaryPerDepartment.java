import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept,
    int salary
){}

public class HighestSalaryPerDepartment {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex","IT",90000),
                new Employee("Sam","IT",85000),
                new Employee("John","HR",70000),
                new Employee("Emma","HR",95000)
            );

        Map<String,Employee> result=

            employees.stream()
                     .collect(
                         Collectors.toMap(
                             Employee::dept,
                             e->e,
                             BinaryOperator.maxBy(
                                 Comparator.comparingInt(
                                     Employee::salary
                                 )
                             )
                         )
                     );

        System.out.println(result);
    }
}
