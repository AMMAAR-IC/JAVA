import java.util.*;
import java.util.stream.*;

record Employee(
    String name,
    String dept
){}

public class EmployeePairs {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee("Alex","IT"),
                new Employee("Sam","IT"),
                new Employee("John","HR"),
                new Employee("Emma","HR")
            );

        IntStream.range(
                    0,
                    employees.size()
                 )
                 .boxed()
                 .flatMap(
                     i ->
                     IntStream.range(
                         i+1,
                         employees.size()
                     )
                     .filter(
                         j ->
                         employees.get(i)
                                  .dept()
                                  .equals(
                                      employees.get(j)
                                          .dept()
                                  )
                     )
                     .mapToObj(
                         j ->
                         employees.get(i).name()
                         +" - "
                         +employees.get(j).name()
                     )
                 )
                 .forEach(
                     System.out::println
                 );
    }
}
