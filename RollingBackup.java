import java.io.*;
import java.time.*;

public class RollingBackup {
    public static void main(String[] args) throws Exception {
        File src = new File("data.txt");
        File dst = new File("backup_" +
                LocalDateTime.now().toString().replace(":","-") + ".txt");

        try (FileInputStream in = new FileInputStream(src);
             FileOutputStream out = new FileOutputStream(dst)) {
            in.transferTo(out);
        }
    }
}
