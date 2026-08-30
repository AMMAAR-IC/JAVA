import java.util.*;
import java.util.stream.*;

public class IncreasingSequence {

    public static void main(String[] args){

        int[] nums=
            {1,2,3,2,3,4,5,1};

        IntStream.range(
                    0,
                    nums.length-1
                 )
                 .filter(
                     i -> nums[i+1]>nums[i]
                 )
                 .forEach(
                     i ->
                     System.out.println(
                         nums[i]
                         +" < "
                         +nums[i+1]
                     )
                 );
    }
}
