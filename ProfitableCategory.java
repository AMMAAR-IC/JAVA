import java.util.*;
import java.util.stream.*;

record Sale(
    String category,
    int revenue,
    int cost
){}

public class ProfitableCategory {

    public static void main(String[] args){

        List<Sale> sales =
            List.of(
                new Sale("Electronics",1000,700),
                new Sale("Electronics",1500,900),

                new Sale("Furniture",800,300),
                new Sale("Furniture",700,500),

                new Sale("Clothing",600,200)
            );

        sales.stream()
             .collect(
                 Collectors.groupingBy(
                     Sale::category,
                     Collectors.summingInt(
                         s ->
                         s.revenue()
                         - s.cost()
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
             .forEach(
                 System.out::println
             );
    }
}
