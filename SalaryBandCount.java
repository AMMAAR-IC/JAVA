import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    int salary
){}

public class SalaryBandCount {

    public static void main(String[] args){

        List<Employee> list=
            List.of(
                new Employee("Alex",35000),
                new Employee("Sam",65000),
                new Employee("John",120000),
                new Employee("Emma",80000)
            );

        Map<String,Long> result=

            list.stream()
                .collect(
                    Collectors.groupingBy(
                        e->{

                            if(e.salary()<50000)
                                return "LOW";

                            if(e.salary()<100000)
                                return "MEDIUM";

                            return "HIGH";

                        },
                        Collectors.counting()
                    )
                );

        System.out.println(result);
    }
}
