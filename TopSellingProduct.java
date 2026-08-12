import java.util.*;
import java.util.stream.*;

record Sale(
    String product,
    int quantity
){}

public class TopSellingProduct {

    public static void main(String[] args){

        List<Sale> sales=
            List.of(
                new Sale("Laptop",5),
                new Sale("Phone",8),
                new Sale("Laptop",7),
                new Sale("Phone",3),
                new Sale("Mouse",10)
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
             .max(
                 Map.Entry.comparingByValue()
             )
             .ifPresent(
                 System.out::println
             );
    }
}
