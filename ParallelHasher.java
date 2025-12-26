import java.nio.file.*;
import java.security.*;
import java.util.concurrent.*;

public class ParallelHasher {
    public static void main(String[] args) throws Exception {
        ExecutorService pool=Executors.newFixedThreadPool(6);

        for(Path p:Files.list(Path.of(".")).toList())
            if(Files.isRegularFile(p))
                pool.submit(() -> hash(p));

        pool.shutdown();
    }

    static void hash(Path p){
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[] b=Files.readAllBytes(p);
            md.update(b);
            System.out.println(p+" -> "+bytes(md.digest()));
        }catch(Exception ignored){}
    }

    static String bytes(byte[] a){
        StringBuilder s=new StringBuilder();
        for(byte x:a)s.append(String.format("%02x",x));
        return s.toString();
    }
}
