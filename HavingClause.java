import java.util.*;
import java.util.stream.*;

record Sale(
    String product,
    int quantity
){}

public class HavingClause {

    public static void main(String[] args){

        List<Sale> sales=
            List.of(
                new Sale("Laptop",5),
                new Sale("Laptop",8),
                new Sale("Phone",2),
                new Sale("Phone",4)
            );

        sales.stream()
             .collect(
                 Collectors.groupingBy(
                     Sale::product,
                     Collectors.summingInt(
                         Sale::quantity
                     )
                 )
             )
             .entrySet()
             .stream()
             .filter(
                 e->e.getValue()>10
             )
             .forEach(System.out::println);
    }
}
