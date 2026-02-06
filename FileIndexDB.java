import java.io.*;
import java.util.*;

public class FileIndexDB {
    static Map<String,String> db = new HashMap<>();

    public static void main(String[] args) {
        scan(new File("."));
        System.out.println(db.keySet().stream().limit(10).toList());
    }

    static void scan(File f){
        if(f.isFile()) db.put(f.getName(), f.getAbsolutePath());
        else for(File c : f.listFiles()) scan(c);
    }
}
