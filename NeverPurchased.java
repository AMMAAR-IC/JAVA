import java.util.*;

record Product(
    String name
){}

record Purchase(
    String product
){}

public class NeverPurchased {

    public static void main(String[] args){

        List<Product> products=
            List.of(
                new Product("Laptop"),
                new Product("Phone"),
                new Product("Tablet"),
                new Product("Mouse")
            );

        List<Purchase> purchases=
            List.of(
                new Purchase("Laptop"),
                new Purchase("Phone")
            );

        Set<String> purchased=
            purchases.stream()
                     .map(Purchase::product)
                     .collect(
                         java.util.stream.Collectors.toSet()
                     );

        products.stream()
                .filter(
                    p -> !purchased.contains(
                        p.name()
                    )
                )
                .forEach(
                    System.out::println
                );
    }
}
