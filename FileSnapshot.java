import java.nio.file.*;
import java.util.*;

public class FileSnapshot {
    static Map<String,Long> snap(Path d)throws Exception{
        Map<String,Long> m=new HashMap<>();
        for(Path p:Files.list(d).toList())
            if(Files.isRegularFile(p))
                m.put(p.getFileName().toString(), Files.getLastModifiedTime(p).toMillis());
        return m;
    }

    public static void main(String[] args)throws Exception{
        var a=snap(Path.of("."));
        Thread.sleep(2000);
        var b=snap(Path.of("."));
        System.out.println(a.equals(b)?"No change":"Changed");
    }
}
