import java.util.stream.*;

public class SlidingWindowMaximum {

    public static void main(String[] args){

        int[] arr=
            {1,3,2,5,4,6};

        IntStream.range(
                    0,
                    arr.length-2
                 )
                 .map(
                    i->
                    Math.max(
                        arr[i],
                        Math.max(
                            arr[i+1],
                            arr[i+2]
                        )
                    )
                 )
                 .forEach(
                    System.out::println
                 );
    }
}
