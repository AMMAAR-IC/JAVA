import java.io.*;

public class WAL {
    static void log(String entry)throws Exception{
        try(FileWriter w=new FileWriter("wal.log",true)){
            w.write(entry+"\n");
        }
    }

    public static void main(String[] args)throws Exception{
        log("SET x=10");
        log("SET y=20");
    }
}
