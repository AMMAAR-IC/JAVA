import java.util.*;

public class HexNumbers {

    public static void main(String[] args){

        List<Integer> nums=
            List.of(
                10,
                15,
                255,
                512
            );

        nums.stream()
            .map(
                Integer::toHexString
            )
            .forEach(System.out::println);
    }
}
