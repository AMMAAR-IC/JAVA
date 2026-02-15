import java.io.*;

public class MiniFileDB {
    public static void main(String[] args) throws Exception {
        write("db.dat", "user=ammaar");
        write("db.dat", "lang=java");

        read("db.dat");
    }

    static void write(String file, String record) throws Exception {
        try(FileWriter w = new FileWriter(file, true)){
            w.write(record + "\n");
        }
    }

    static void read(String file) throws Exception {
        try(BufferedReader r = new BufferedReader(new FileReader(file))){
            String line;
            while((line=r.readLine())!=null)
                System.out.println(line);
        }
    }
}
