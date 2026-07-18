import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    int salary
){}

public class SalaryRangeGrouping {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex",40000),
                new Employee("Sam",75000),
                new Employee("John",120000)
            );

        Map<String,List<Employee>> result=

            employees.stream()
                     .collect(
                         Collectors.groupingBy(
                             e->{
                                 if(e.salary()<50000)
                                     return "LOW";

                                 if(e.salary()<100000)
                                     return "MEDIUM";

                                 return "HIGH";
                             }
                         )
                     );

        System.out.println(result);
    }
}
