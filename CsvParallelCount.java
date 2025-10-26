// CsvParallelCount.java
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class CsvParallelCount {
    public static void main(String[] args) throws Exception {
        var lines = Files.lines(Path.of("big.csv"));
        ExecutorService ex = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<CompletableFuture<Map<String,Long>>> futures = lines
            .map(line -> CompletableFuture.supplyAsync(() -> {
                String key = line.split(",")[2]; // column 3 as key
                Map<String,Long> m = new HashMap<>();
                m.put(key, 1L);
                return m;
            }, ex))
            .collect(Collectors.toList());

        Map<String,Long> result = new HashMap<>();
        for (var f : futures) {
            f.join().forEach((k,v) -> result.merge(k, v, Long::sum));
        }
        ex.shutdown();
        result.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed()).limit(20).forEach(System.out::println);
    }
}
