import java.util.*;

public class FirstRepeatedTransaction {

    public static void main(String[] args){

        List<String> transactions=
            List.of(
                "TX100",
                "TX200",
                "TX300",
                "TX200",
                "TX400"
            );

        Set<String> seen=
            new HashSet<>();

        String result=

            transactions.stream()
                        .filter(
                            tx->!seen.add(tx)
                        )
                        .findFirst()
                        .orElse(
                            "No duplicate"
                        );

        System.out.println(result);
    }
}
