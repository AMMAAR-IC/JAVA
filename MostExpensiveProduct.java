import java.util.*;
import java.util.stream.*;

record Product(
    String name,
    String category,
    int price
){}

public class MostExpensiveProduct {

    public static void main(String[] args){

        List<Product> products=
            List.of(
                new Product("Laptop","Electronics",900),
                new Product("Phone","Electronics",700),
                new Product("Chair","Furniture",300),
                new Product("Desk","Furniture",500)
            );

        Map<String,Product> result=

            products.stream()
                    .collect(
                        Collectors.toMap(
                            Product::category,
                            p -> p,
                            BinaryOperator.maxBy(
                                Comparator.comparingInt(
                                    Product::price
                                )
                            )
                        )
                    );

        System.out.println(result);
    }
}
