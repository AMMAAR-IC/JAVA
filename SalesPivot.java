import java.util.*;
import java.util.stream.*;

record Sale(
    int year,
    String quarter,
    int amount
){}

public class SalesPivot {

    public static void main(String[] args){

        List<Sale> sales=
            List.of(
                new Sale(2025,"Q1",100),
                new Sale(2025,"Q2",200),
                new Sale(2026,"Q1",150)
            );

        Map<Integer,Map<String,Integer>> result=

            sales.stream()
                 .collect(
                     Collectors.groupingBy(
                         Sale::year,
                         Collectors.groupingBy(
                             Sale::quarter,
                             Collectors.summingInt(
                                 Sale::amount
                             )
                         )
                     )
                 );

        System.out.println(result);
    }
}
