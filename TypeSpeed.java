import java.io.*;

public class TypeSpeed {
    public static void main(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec("xinput test-xi2 --root");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        int count = 0;
        long start = System.currentTimeMillis();
        System.out.println("Start typing…");

        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("RawKeyPress")) count++;

            long now = System.currentTimeMillis();
            if (now - start >= 60000) {
                System.out.println("WPM: " + count);
                count = 0;
                start = now;
            }
        }
    }
}
