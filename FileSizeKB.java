import java.io.File;

public class FileSizeKB {

    public static void main(String[] args){

        File f=new File("data.txt");

        System.out.println(f.length()/1024+" KB");
    }
}
