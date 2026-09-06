import java.util.*;
import java.util.stream.*;

record Product(
    String name,
    double revenue,
    double rating,
    int sales
){}

public class BestProduct {

    public static void main(String[] args){

        List<Product> products =
            List.of(
                new Product(
                    "Laptop",
                    90000,
                    4.5,
                    100
                ),

                new Product(
                    "Phone",
                    80000,
                    4.8,
                    150
                ),

                new Product(
                    "Tablet",
                    70000,
                    4.2,
                    80
                )
            );

        products.stream()
                .sorted(
                    Comparator
                    .comparingDouble(
                        Product::rating
                    )
                    .thenComparingDouble(
                        Product::revenue
                    )
                    .thenComparingInt(
                        Product::sales
                    )
                    .reversed()
                )
                .forEach(
                    System.out::println
                );
    }
}
