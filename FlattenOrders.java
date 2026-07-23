import java.util.*;

record Order(
    int id,
    List<String> products
){}

public class FlattenOrders {

    public static void main(String[] args){

        List<Order> orders=
            List.of(
                new Order(
                    1,
                    List.of("Laptop","Mouse")
                ),
                new Order(
                    2,
                    List.of("Phone","Charger")
                )
            );

        orders.stream()
              .flatMap(
                  o->o.products().stream()
              )
              .distinct()
              .sorted()
              .forEach(
                  System.out::println
              );
    }
}
