import java.io.*;

public class ProcessList {
    public static void main(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec("ps -eo pid,comm,%mem --sort=-%mem");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            br.lines().limit(15).forEach(System.out::println);
        }
    }
}
