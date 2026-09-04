import java.util.*;
import java.util.stream.*;

record Transaction(
    String id,
    double amount
){}

public class TransactionMismatch {

    public static void main(String[] args){

        List<Transaction> bank=
            List.of(
                new Transaction("TX1",500),
                new Transaction("TX2",700),
                new Transaction("TX3",900)
            );

        List<Transaction> internal=
            List.of(
                new Transaction("TX1",500),
                new Transaction("TX2",750),
                new Transaction("TX3",900)
            );

        Map<String,Double> internalMap=

            internal.stream()
                    .collect(
                        Collectors.toMap(
                            Transaction::id,
                            Transaction::amount
                        )
                    );

        bank.stream()
            .filter(
                t ->
                internalMap.containsKey(t.id())
                &&
                Double.compare(
                    t.amount(),
                    internalMap.get(t.id())
                ) != 0
            )
            .forEach(
                System.out::println
            );
    }
}
