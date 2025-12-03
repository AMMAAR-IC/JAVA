import java.io.*;
import java.util.zip.*;
import java.nio.file.*;

public class CompressionBench {
    public static void main(String[] args) throws Exception {
        byte[] data = Files.readAllBytes(Path.of("big.txt"));

        long t1=System.nanoTime();
        byte[] def=deflate(data);
        long t2=System.nanoTime();
        byte[] gz=gzip(data);
        long t3=System.nanoTime();

        System.out.printf("Deflate: %d bytes in %.2f ms%n", def.length,(t2-t1)/1e6);
        System.out.printf("GZIP:    %d bytes in %.2f ms%n", gz.length,(t3-t2)/1e6);
    }

    static byte[] deflate(byte[] b)throws IOException{
        ByteArrayOutputStream o=new ByteArrayOutputStream();
        DeflaterOutputStream d=new DeflaterOutputStream(o); d.write(b); d.close();
        return o.toByteArray();
    }
    static byte[] gzip(byte[] b)throws IOException{
        ByteArrayOutputStream o=new ByteArrayOutputStream();
        GZIPOutputStream g=new GZIPOutputStream(o); g.write(b); g.close();
        return o.toByteArray();
    }
}
