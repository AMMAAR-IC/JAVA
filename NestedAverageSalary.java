import java.util.*;
import java.util.stream.*;

record Employee(
    String dept,
    int year,
    int salary
){}

public class NestedAverageSalary {

    public static void main(String[] args){

        List<Employee> list=
            List.of(
                new Employee("IT",2024,70000),
                new Employee("IT",2025,90000),
                new Employee("HR",2024,50000),
                new Employee("HR",2025,80000)
            );

        Map<String,
            Map<Integer,Double>> result=

            list.stream()
                .collect(
                    Collectors.groupingBy(
                        Employee::dept,
                        Collectors.groupingBy(
                            Employee::year,
                            Collectors.averagingInt(
                                Employee::salary
                            )
                        )
                    )
                );

        System.out.println(result);
    }
}
