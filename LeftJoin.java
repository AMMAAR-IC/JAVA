import java.util.*;
import java.util.stream.*;

record Employee(
    int deptId,
    String name
){}

record Department(
    int id,
    String name
){}

public class LeftJoin {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee(1,"Alex"),
                new Employee(5,"Mike")
            );

        List<Department> departments=
            List.of(
                new Department(1,"IT")
            );

        employees.stream()
                 .map(e->{

                     String dept=

                         departments.stream()
                                    .filter(d->
                                        d.id()==e.deptId()
                                    )
                                    .map(
                                        Department::name
                                    )
                                    .findFirst()
                                    .orElse("Unknown");

                     return e.name()+" -> "+dept;

                 })
                 .forEach(System.out::println);
    }
}
