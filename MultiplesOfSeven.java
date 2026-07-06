import java.util.stream.*;

public class MultiplesOfSeven {

    public static void main(String[] args){

        IntStream.rangeClosed(1,200)
                 .filter(n->n%7==0)
                 .forEach(System.out::println);
    }
}
