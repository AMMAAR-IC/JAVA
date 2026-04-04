import java.io.File;
import java.util.*;

public class FileSortLen {

    public static void main(String[] args){

        File dir=new File(".");

        String[] files=dir.list();

        Arrays.sort(files,Comparator.comparingInt(String::length));

        for(String f:files)
            System.out.println(f);
    }
}
