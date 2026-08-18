import java.util.*;
import java.util.stream.*;

record Order(
    String customer,
    double amount
){}

public class MostValuableCustomer {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order("Alex",500),
                new Order("Sam",900),
                new Order("Alex",700),
                new Order("John",400),
                new Order("Sam",300)
            );

        orders.stream()
              .collect(
                  Collectors.groupingBy(
                      Order::customer,
                      Collectors.summingDouble(
                          Order::amount
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
