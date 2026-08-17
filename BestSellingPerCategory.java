import java.util.*;
import java.util.stream.*;

record Sale(
    String category,
    String product,
    int quantity
){}

public class BestSellingPerCategory {

    public static void main(String[] args){

        List<Sale> sales=
            List.of(
                new Sale("Electronics","Laptop",10),
                new Sale("Electronics","Phone",20),
                new Sale("Electronics","Laptop",15),
                new Sale("Furniture","Chair",12),
                new Sale("Furniture","Desk",20),
                new Sale("Furniture","Chair",8)
            );

        sales.stream()
             .collect(
                 Collectors.groupingBy(
                     Sale::category,
                     Collectors.groupingBy(
                         Sale::product,
                         Collectors.summingInt(
                             Sale::quantity
                         )
                     )
                 )
             )
             .forEach(
                 (category,products) ->

                     products.entrySet()
                             .stream()
                             .max(
                                 Map.Entry
                                 .comparingByValue()
                             )
                             .ifPresent(
                                 best ->
                                 System.out.println(
                                     category
                                     +" -> "
                                     +best
                                 )
                             )
             );
    }
}
