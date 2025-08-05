import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessScanner {
    public static void main(String[] args) {
        String[] suspicious = {"keylog", "inject", "miner", "crypto", "bot", "mal"};

        try {
            Process p = Runtime.getRuntime().exec("ps -e");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                for (String s : suspicious) {
                    if (line.toLowerCase().contains(s)) {
                        System.out.println("⚠️ Suspicious Process: " + line.trim());
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
