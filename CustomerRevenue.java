import java.util.*;
import java.util.stream.*;

record Order(
    String customer,
    int amount
){}

public class CustomerRevenue {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order("Alice",200),
                new Order("Bob",150),
                new Order("Alice",400),
                new Order("Bob",500)
            );

        orders.stream()
              .collect(
                  Collectors.groupingBy(
                      Order::customer,
                      Collectors.summingInt(
                          Order::amount
                      )
                  )
              )
              .entrySet()
              .stream()
              .max(Map.Entry.comparingByValue())
              .ifPresent(System.out::println);
    }
}
