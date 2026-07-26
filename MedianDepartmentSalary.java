import java.util.*;
import java.util.stream.*;

record Employee(
    String dept,
    int salary
){}

public class MedianDepartmentSalary {

    public static void main(String[] args){

        List<Employee> list=
            List.of(
                new Employee("IT",60),
                new Employee("IT",90),
                new Employee("IT",70),
                new Employee("HR",50),
                new Employee("HR",80)
            );

        Map<String,List<Integer>> result=

            list.stream()
                .collect(
                    Collectors.groupingBy(
                        Employee::dept,
                        Collectors.mapping(
                            Employee::salary,
                            Collectors.toList()
                        )
                    )
                );

        System.out.println(result);
    }
}
