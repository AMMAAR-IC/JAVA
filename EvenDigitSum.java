import java.util.stream.*;

public class EvenDigitSum {

    public static void main(String[] args){

        IntStream.rangeClosed(1,100)
                 .filter(n->

                     String.valueOf(n)
                           .chars()
                           .map(c->c-'0')
                           .sum()%2==0

                 )
                 .forEach(System.out::println);
    }
}
