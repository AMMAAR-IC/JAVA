import java.util.*;

public class ShuffleArray {

    public static void main(String[] args){

        Integer[] arr = {1,2,3,4,5};

        Collections.shuffle(Arrays.asList(arr));

        System.out.println(Arrays.toString(arr));
    }
}
