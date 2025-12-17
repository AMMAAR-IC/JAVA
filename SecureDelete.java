import java.io.*;

public class SecureDelete {
    public static void main(String[] args) throws Exception {
        File f = new File("secret.txt");
        RandomAccessFile raf = new RandomAccessFile(f, "rw");

        for (int i = 0; i < f.length(); i++)
            raf.write(0);

        raf.close();
        f.delete();
        System.out.println("File securely deleted");
    }
}
