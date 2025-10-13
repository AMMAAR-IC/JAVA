import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessMonitor {
    public static void main(String[] args) {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;
            if (os.contains("win")) {
                process = Runtime.getRuntime().exec("tasklist");
            } else {
                process = Runtime.getRuntime().exec("ps -e");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
