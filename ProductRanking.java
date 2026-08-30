import java.util.*;
import java.util.stream.*;

record Sale(
    String product,
    int price,
    int quantity
){}

public class ProductRanking {

    public static void main(String[] args){

        List<Sale> sales=
            List.of(
                new Sale("Laptop",700,2),
                new Sale("Phone",500,5),
                new Sale("Laptop",700,1),
                new Sale("Mouse",50,20)
            );

        sales.stream()
             .collect(
                 Collectors.groupingBy(
                     Sale::product,
                     Collectors.summingInt(
                         s ->
                         s.price()*s.quantity()
                     )
                 )
             )
             .entrySet()
             .stream()
             .sorted(
                 Map.Entry
                 .<String,Integer>
                 comparingByValue()
                 .reversed()
             )
             .map(
                 e ->
                 e.getKey()
                 +" -> ₹"
                 +e.getValue()
             )
             .forEach(
                 System.out::println
             );
    }
}
