import java.util.*;
import java.util.stream.*;

record Sale(
    String region,
    String product,
    int amount
){}

public class PivotTable {

    public static void main(String[] args){

        List<Sale> sales=
            List.of(
                new Sale("North","Laptop",10),
                new Sale("North","Phone",20),
                new Sale("South","Laptop",15),
                new Sale("South","Phone",30)
            );

        Map<String,
            Map<String,Integer>> pivot=

            sales.stream()
                 .collect(
                     Collectors.groupingBy(
                         Sale::region,
                         Collectors.groupingBy(
                             Sale::product,
                             Collectors.summingInt(
                                 Sale::amount
                             )
                         )
                     )
                 );

        System.out.println(pivot);
    }
}
