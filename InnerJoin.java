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

public class InnerJoin {

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee(1,"Alex"),
                new Employee(2,"Sam"),
                new Employee(1,"John")
            );

        List<Department> departments=
            List.of(
                new Department(1,"IT"),
                new Department(2,"HR")
            );

        employees.stream()
                 .flatMap(e->

                     departments.stream()
                                .filter(d->
                                    d.id()==e.deptId()
                                )
                                .map(d->
                                    e.name()+" -> "+d.name()
                                )

                 )
                 .forEach(System.out::println);
    }
}
