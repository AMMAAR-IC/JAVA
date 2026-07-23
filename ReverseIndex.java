import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept
){}

public class ReverseIndex {

    public static void main(String[] args){

        List<Employee> list=
            List.of(
                new Employee("Alex","IT"),
                new Employee("Sam","IT"),
                new Employee("Emma","HR")
            );

        Map<String,String> map=

            list.stream()
                .collect(
                    Collectors.toMap(
                        Employee::name,
                        Employee::dept
                    )
                );

        System.out.println(map);
    }
}
