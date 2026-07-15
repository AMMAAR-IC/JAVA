import java.util.stream.*;

public class SlidingWindow {

    public static void main(String[] args){

        int[] nums=
            {1,2,3,4,5,6};

        IntStream.range(
                    0,
                    nums.length-2
                 )
                 .map(i->

                    nums[i]
                    +nums[i+1]
                    +nums[i+2]

                 )
                 .forEach(
                     System.out::println
                 );
    }
}
