import java.io.File;

public class CountRecursive {

    static int count(File f){
        if(f.isFile()) return 1;

        int c=0;

        for(File x:f.listFiles())
            c+=count(x);

        return c;
    }

    public static void main(String[] args){

        System.out.println(count(new File(".")));
    }
}
