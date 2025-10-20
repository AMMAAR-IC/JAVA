// MultiDownloader.java
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiDownloader {
    public static void download(String url, String outFile, int threads) throws Exception {
        HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
        int len = conn.getContentLength();
        RandomAccessFile raf = new RandomAccessFile(outFile, "rw");
        raf.setLength(len);
        raf.close();
        ExecutorService ex = Executors.newFixedThreadPool(threads);
        int chunk = (len + threads -1)/threads;
        for (int i=0;i<threads;i++) {
            final int start = i*chunk;
            final int end = Math.min(len-1, (i+1)*chunk -1);
            ex.submit(() -> {
                try {
                    HttpURLConnection c = (HttpURLConnection)new URL(url).openConnection();
                    c.setRequestProperty("Range","bytes=" + start + "-" + end);
                    try (InputStream in = c.getInputStream(); RandomAccessFile f = new RandomAccessFile(outFile,"rw")) {
                        f.seek(start);
                        byte[] buf = new byte[8192]; int r;
                        while ((r=in.read(buf))!=-1) f.write(buf,0,r);
                    }
                } catch (Exception e) { e.printStackTrace(); }
            });
        }
        ex.shutdown(); ex.awaitTermination(1, TimeUnit.HOURS);
    }

    public static void main(String[] args) throws Exception {
        download("https://speed.hetzner.de/100MB.bin", "100MB.bin", 4);
        System.out.println("Done");
    }
}
