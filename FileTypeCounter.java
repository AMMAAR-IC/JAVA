import java.io.File;
import java.util.*;

public class FileTypeCounter {

    public static void main(String[] args){

        Map<String,Integer> map=new HashMap<>();

        for(File f:new File(".").listFiles()){
            if(f.isFile()){
                String name=f.getName();
                int i=name.lastIndexOf(".");
                if(i>0){
                    String ext=name.substring(i+1);
                    map.merge(ext,1,Integer::sum);
                }
            }
        }

        System.out.println(map);
    }
}
