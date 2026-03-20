import java.io.File;
import java.util.*;

public class RandomFile {

    public static void main(String[] args){

        File[] files = new File(".").listFiles();

        if(files.length > 0){
            Random r = new Random();
            System.out.println(files[r.nextInt(files.length)].getName());
        }
    }
}
