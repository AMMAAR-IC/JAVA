import java.util.*;

public class ArmstrongList {

    static boolean armstrong(int n){

        String s=String.valueOf(n);

        int p=s.length();

        int sum=
            s.chars()
             .map(c->
                 (int)Math.pow(
                     c-'0',
                     p
                 )
             )
             .sum();

        return sum==n;
    }

    public static void main(String[] args){

        List<Integer> nums=
            List.of(
                153,
                370,
                371,
                407,
                123
            );

        nums.stream()
            .filter(
                ArmstrongList::armstrong
            )
            .forEach(System.out::println);
    }
}
