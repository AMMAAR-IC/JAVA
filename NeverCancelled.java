import java.util.*;
import java.util.stream.*;

record Order(
    String customer,
    String status
){}

public class NeverCancelled {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order("Alex","COMPLETED"),
                new Order("Alex","COMPLETED"),

                new Order("Sam","COMPLETED"),
                new Order("Sam","CANCELLED"),

                new Order("John","COMPLETED")
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
                   .noneMatch(
                       o -> o.status()
                            .equals("CANCELLED")
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
