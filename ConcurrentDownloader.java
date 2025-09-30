// ConcurrentDownloader.java
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ConcurrentDownloader {
    public static void download(String url, String out) throws Exception {
        HttpURLConnection c = (HttpURLConnection)new URL(url).openConnection();
        int len = c.getContentLength();
        try (InputStream in = c.getInputStream(); FileOutputStream fos = new FileOutputStream(out)) {
            byte[] buf = new byte[8192]; int r; int read=0;
            while ((r = in.read(buf)) != -1) {
                fos.write(buf,0,r);
                read += r;
                if (len>0) System.out.printf("\r%s: %d/%d bytes", out, read, len);
            }
            System.out.println("\nDone: " + out);
        }
    }

    public static void main(String[] args) throws Exception {
        String[] urls = { "https://speed.hetzner.de/100MB.bin", "https://speed.hetzner.de/50MB.bin" };
        ExecutorService ex = Executors.newFixedThreadPool(3);
        for (int i=0;i<urls.length;i++) {
            final int idx=i;
            ex.submit(() -> {
                try { download(urls[idx], "file"+idx+".bin"); }
                catch(Exception e){ System.out.println("Err: "+e.getMessage()); }
            });
        }
        ex.shutdown(); ex.awaitTermination(30, TimeUnit.MINUTES);
    }
}
