import java.util.*;
import java.util.stream.*;

record Student(
    String dept,
    int year,
    String name
){}

public class MultiGrouping {

    public static void main(String[] args){

        List<Student> students=
            List.of(
                new Student("CS",1,"Alex"),
                new Student("CS",2,"Sam"),
                new Student("IT",1,"John"),
                new Student("IT",2,"Emma")
            );

        Map<String,
            Map<Integer,
                List<Student>>> result=

            students.stream()
                    .collect(
                        Collectors.groupingBy(
                            Student::dept,
                            Collectors.groupingBy(
                                Student::year
                            )
                        )
                    );

        System.out.println(result);
    }
}
