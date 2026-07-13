import java.util.*;

public class CartesianProduct {

    public static void main(String[] args){

        List<String> colors=
            List.of(
                "Red",
                "Blue"
            );

        List<String> sizes=
            List.of(
                "S",
                "M",
                "L"
            );

        colors.stream()
              .flatMap(c->

                  sizes.stream()
                       .map(
                           s->c+"-"+s
                       )

              )
              .forEach(
                  System.out::println
              );
    }
}
