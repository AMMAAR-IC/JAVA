import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiDownload {
    public static void main(String[] args)throws Exception{
        String url="https://example.com/file.bin";
        int threads=4;
        RandomAccessFile out=new RandomAccessFile("file.bin","rw");
        ExecutorService pool=Executors.newFixedThreadPool(threads);

        long size = new URL(url).openConnection().getContentLength();
        long chunk=size/threads;

        for(int i=0;i<threads;i++){
            long start=i*chunk;
            long end=(i==threads-1)?size-1:start+chunk-1;
            int part=i;
            pool.submit(() -> downloadPart(url,out,start,end,part));
        }
        pool.shutdown();
    }

    static void downloadPart(String u,RandomAccessFile out,long s,long e,int id){
        try{
            HttpURLConnection c=(HttpURLConnection)new URL(u).openConnection();
            c.setRequestProperty("Range","bytes="+s+"-"+e);
            InputStream in=c.getInputStream();
            out.seek(s);
            in.transferTo(out);
            System.out.println("Chunk "+id+" done");
        }catch(Exception ignored){}
    }
}
