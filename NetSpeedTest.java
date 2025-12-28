import java.net.*;
import java.io.*;

public class NetSpeedTest {
    public static void main(String[] args)throws Exception{
        long start=System.currentTimeMillis();
        byte[] data=new URL("https://speed.hetzner.de/100MB.bin")
                .openStream().readNBytes(5_000_000); // 5MB test

        long time=System.currentTimeMillis()-start;
        double mb=(data.length/1024.0/1024.0);
        System.out.printf("Speed: %.2f MB/s\n", mb/(time/1000.0));
    }
}
