import java.io.File;

public class FileSearch {

    static void search(File dir, String name){

        for(File f : dir.listFiles()){

            if(f.getName().contains(name))
                System.out.println(f.getAbsolutePath());

            if(f.isDirectory())
                search(f, name);
        }
    }

    public static void main(String[] args){

        search(new File("."), "java");
    }
}
