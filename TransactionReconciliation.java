import java.util.*;
import java.util.stream.*;

record Transaction(
    String id,
    double amount
){}

public class TransactionReconciliation {

    public static void main(String[] args){

        List<Transaction> bank=
            List.of(
                new Transaction("TX1",500),
                new Transaction("TX2",700),
                new Transaction("TX3",900),
                new Transaction("TX4",300)
            );

        List<Transaction> internal=
            List.of(
                new Transaction("TX1",500),
                new Transaction("TX2",700),
                new Transaction("TX4",300)
            );

        Set<String> internalIds=

            internal.stream()
                    .map(Transaction::id)
                    .collect(Collectors.toSet());

        bank.stream()
            .filter(
                t -> !internalIds.contains(t.id())
            )
            .forEach(
                System.out::println
            );
    }
}
