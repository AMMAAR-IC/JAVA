// ProcessTool.java
import java.io.*;
import java.util.*;

public class ProcessTool {
    public static void main(String[] args) throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        Process p = os.contains("win") ? Runtime.getRuntime().exec("tasklist") : Runtime.getRuntime().exec("ps -e -o pid,comm");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line; while ((line = br.readLine()) != null) System.out.println(line);
        }
        System.out.println("Enter PID to kill (or blank): ");
        Scanner sc = new Scanner(System.in); String pid = sc.nextLine().trim();
        if (!pid.isEmpty()) {
            if (os.contains("win")) Runtime.getRuntime().exec("taskkill /PID " + pid + " /F");
            else Runtime.getRuntime().exec("kill -9 " + pid);
            System.out.println("Kill command issued for " + pid);
        }
    }
}
