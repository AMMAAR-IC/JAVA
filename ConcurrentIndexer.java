import java.nio.file.*;
import java.util.concurrent.*;
import java.util.*;

public class ConcurrentIndexer {
    static ConcurrentHashMap<String, Set<String>> idx = new ConcurrentHashMap<>();

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(6);

        for(Path p : Files.newDirectoryStream(Path.of("."), "*.txt")){
            pool.submit(() -> {
                try{
                    String txt = Files.readString(p).toLowerCase();
                    for(String w : txt.split("\\W+")){
                        idx.computeIfAbsent(w,k->ConcurrentHashMap.newKeySet())
                                .add(p.getFileName().toString());
                    }
                }catch(Exception ignored){}
            });
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("java -> " + idx.get("java"));
    }
}
