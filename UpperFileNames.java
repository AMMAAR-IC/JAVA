import java.io.File;

public class UpperFileNames {

    public static void main(String[] args){

        for(File f:new File(".").listFiles())
            System.out.println(f.getName().toUpperCase());
    }
}
