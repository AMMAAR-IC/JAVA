import java.io.File;

public class FileCompare {

    public static void main(String[] args){

        File a=new File("a.txt");
        File b=new File("b.txt");

        System.out.println(a.length()>b.length?"A bigger":"B bigger");
    }
}
