import java.io.*;
import java.util.*;

public class TopLargestFiles {
    static List<File> all = new ArrayList<>();

    static void scan(File f){
        if(f.isFile()) all.add(f);
        else for(File c:f.listFiles()) scan(c);
    }

    public static void main(String[] args){
        scan(new File("."));
        all.sort((a,b)->Long.compare(b.length(),a.length()));

        for(int i=0;i<Math.min(5,all.size());i++)
            System.out.println(all.get(i).getName()+" -> "+all.get(i).length());
    }
}
