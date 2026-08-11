import java.util.*;
import java.util.stream.*;

record Order(
    String customer,
    double amount
){}

public class CustomerLifetimeValue {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order("Alex",500),
                new Order("Alex",700),
                new Order("Sam",300),
                new Order("Sam",900)
            );

        Map<String,Double> result=

            orders.stream()
                  .collect(
                      Collectors.groupingBy(
                          Order::customer,
                          Collectors.summingDouble(
                              Order::amount
                          )
                      )
                  );

        result.entrySet()
              .stream()
              .sorted(
                  Map.Entry
                     .<String,Double>
                     comparingByValue()
                     .reversed()
              )
              .forEach(
                  System.out::println
              );
    }
}
