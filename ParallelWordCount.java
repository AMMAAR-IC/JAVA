import java.nio.file.*;
import java.util.concurrent.*;

public class ParallelWordCount {
    public static void main(String[] args)throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for(Path p:Files.newDirectoryStream(Path.of("."),"*.txt")){
            pool.submit(() ->
                System.out.println(p + " -> " +
                    Files.readString(p).split("\\W+").length));
        }
        pool.shutdown();
    }
}
