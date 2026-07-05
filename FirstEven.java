import java.util.*;

public class FirstEven {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(1,3,7,8,10);

        int result=
            nums.stream()
                .filter(n->n%2==0)
                .findFirst()
                .orElse(-1);

        System.out.println(result);
    }
}
