import java.util.*;

record Item(
    String name,
    int price
){}

record Order(
    int id,
    List<Item> items
){}

public class OrderRevenue {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order(
                    1,
                    List.of(
                        new Item("Laptop",700),
                        new Item("Mouse",50)
                    )
                ),
                new Order(
                    2,
                    List.of(
                        new Item("Phone",600)
                    )
                )
            );

        int revenue=

            orders.stream()
                  .flatMap(
                      o->o.items().stream()
                  )
                  .mapToInt(
                      Item::price
                  )
                  .sum();

        System.out.println(revenue);
    }
}
