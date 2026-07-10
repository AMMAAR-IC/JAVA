import java.util.stream.*;

public class ContainsFive {

    public static void main(String[] args){

        IntStream.rangeClosed(1,200)
                 .filter(n->

                     String.valueOf(n)
                           .contains("5")

                 )
                 .forEach(System.out::println);
    }
}
