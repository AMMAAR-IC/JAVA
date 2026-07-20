import java.util.*;

record Employee(
    String name,
    int salary
){}

public class Top3Salary {

    public static void main(String[] args){

        List<Employee> list=
            List.of(
                new Employee("Alex",50000),
                new Employee("Sam",90000),
                new Employee("Emma",80000),
                new Employee("John",95000),
                new Employee("Mike",60000)
            );

        list.stream()
            .sorted(
                Comparator.comparingInt(
                    Employee::salary
                ).reversed()
            )
            .limit(3)
            .forEach(
                System.out::println
            );
    }
}
