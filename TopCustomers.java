import java.util.*;
import java.util.stream.*;

record Order(
    String customer,
    double amount
){}

public class TopCustomers {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order("Alex",500),
                new Order("Sam",1000),
                new Order("Alex",700),
                new Order("Emma",1200),
                new Order("Sam",400),
                new Order("John",300)
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
              .sorted(
                  Map.Entry
                  .<String,Double>
                  comparingByValue()
                  .reversed()
              )
              .limit(3)
              .forEach(
                  System.out::println
              );
    }
}
