import java.io.File;

public class FileNameLength {

    public static void main(String[] args){

        for(File f : new File(".").listFiles())
            System.out.println(
                f.getName()+" -> "+f.getName().length()
            );
    }
}
