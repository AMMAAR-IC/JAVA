import java.nio.file.*;

public class ByteFrequency {
    public static void main(String[] args)throws Exception{
        byte[] d=Files.readAllBytes(Path.of("file.bin"));
        int[] f=new int[256];
        for(byte b:d) f[b&255]++;

        for(int i=0;i<256;i++)
            if(f[i]>0)
                System.out.println(i+" -> "+f[i]);
    }
}
