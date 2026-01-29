import java.io.*;

public class FileSplitMerge {
    public static void split(String f)throws Exception{
        FileInputStream in=new FileInputStream(f);
        byte[] b=new byte[1024];
        int n,i=0;
        while((n=in.read(b))>0)
            new FileOutputStream(f+"."+i++).write(b,0,n);
    }

    public static void main(String[] args)throws Exception{
        split("big.dat");
    }
}
