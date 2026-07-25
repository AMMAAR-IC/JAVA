import java.util.*;

record Employee(
    int id,
    Integer managerId
){}

public class ManagerHierarchy {

    static boolean hasCycle(
            Map<Integer,Integer> map,
            int employee){

        Set<Integer> visited=
            new HashSet<>();

        Integer current=employee;

        while(current!=null){

            if(!visited.add(current))
                return true;

            current=map.get(current);
        }

        return false;
    }

    public static void main(String[] args){

        List<Employee> employees=
            List.of(
                new Employee(1,2),
                new Employee(2,3),
                new Employee(3,1),
                new Employee(4,null)
            );

        Map<Integer,Integer> managers=

            employees.stream()
                     .collect(
                         Collectors.toMap(
                             Employee::id,
                             Employee::managerId
                         )
                     );

        employees.stream()
                 .filter(
                     e->hasCycle(
                         managers,
                         e.id()
                     )
                 )
                 .forEach(System.out::println);
    }
}
