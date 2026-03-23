import java.io.File;

public class TxtCount {

    public static void main(String[] args){

        int count=0;

        for(File f:new File(".").listFiles())
            if(f.getName().endsWith(".txt"))
                count++;

        System.out.println(count);
    }
}
