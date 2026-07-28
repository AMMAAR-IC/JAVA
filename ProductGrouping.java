import java.util.*;
import java.util.stream.*;

record Product(
    String category,
    String name,
    int price
){}

public class ProductGrouping {

    public static void main(String[] args){

        List<Product> products=
            List.of(
                new Product("Laptop","A",700),
                new Product("Laptop","B",900),
                new Product("Phone","X",300),
                new Product("Phone","Y",600)
            );

        Map<String,List<Product>> result=

            products.stream()
                    .collect(
                        Collectors.groupingBy(
                            Product::category,
                            Collectors.collectingAndThen(
                                Collectors.toList(),
                                list->{
                                    list.sort(
                                        Comparator.comparingInt(
                                            Product::price
                                        )
                                    );
                                    return list;
                                }
                            )
                        )
                    );

        System.out.println(result);
    }
}
