import java.util.*;
import java.util.stream.*;

record Order(
    String customer,
    int amount
){}

public class HighValueCustomers {

    public static void main(String[] args){

        List<Order> orders =
            List.of(
                new Order("Alex",700),
                new Order("Alex",900),

                new Order("Sam",300),
                new Order("Sam",800),

                new Order("John",600),
                new Order("John",750)
            );

        orders.stream()
              .collect(
                  Collectors.groupingBy(
                      Order::customer
                  )
              )
              .entrySet()
              .stream()
              .filter(
                  e ->
                  e.getValue()
                   .stream()
                   .allMatch(
                       o -> o.amount() > 500
                   )
              )
              .map(
                  Map.Entry::getKey
              )
              .forEach(
                  System.out::println
              );
    }
}
