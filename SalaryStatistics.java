import java.util.*;
import java.util.stream.*;

record Employee(
    String dept,
    int salary
){}

public class SalaryStatistics {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("IT",90000),
                new Employee("IT",70000),
                new Employee("HR",60000),
                new Employee("HR",80000)
            );

        Map<String,IntSummaryStatistics> result=

            employees.stream()
                     .collect(
                         Collectors.groupingBy(
                             Employee::dept,
                             Collectors.summarizingInt(
                                 Employee::salary
                             )
                         )
                     );

        System.out.println(result);
    }
}
