import java.util.*;
import java.util.stream.*;

record Item(
    String name,
    int price,
    int quantity
){}

record Order(
    int id,
    List<Item> items
){}

public class OrderTotals {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order(
                    1,
                    List.of(
                        new Item("Laptop",700,1),
                        new Item("Mouse",50,2)
                    )
                ),
                new Order(
                    2,
                    List.of(
                        new Item("Phone",500,2)
                    )
                )
            );

        orders.stream()
              .map(
                  order ->

                  Map.entry(
                      order.id(),

                      order.items()
                           .stream()
                           .mapToInt(
                               i ->
                               i.price()
                               *i.quantity()
                           )
                           .sum()
                  )
              )
              .forEach(
                  System.out::println
              );
    }
}
