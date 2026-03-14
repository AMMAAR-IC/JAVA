import java.io.File;

public class DirList {

    public static void main(String[] args){

        File dir=new File(".");

        for(String f:dir.list())
            System.out.println(f);
    }
}
