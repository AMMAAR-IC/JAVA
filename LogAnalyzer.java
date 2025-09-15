// LogAnalyzer.java
import java.io.*;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) throws Exception {
        File f = new File(args.length>0 ? args[0] : "log.txt");
        String keyword = args.length>1 ? args[1] : "ERROR";
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null)
                if (line.contains(keyword)) count++;
        }
        System.out.println("Keyword '" + keyword + "' found " + count + " times.");
    }
}
