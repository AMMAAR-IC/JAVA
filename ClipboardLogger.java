import java.awt.*;
import java.awt.datatransfer.*;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class ClipboardLogger {
    public static void main(String[] args) throws Exception {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String lastData = "";

        while (true) {
            try {
                String data = (String) clipboard.getData(DataFlavor.stringFlavor);
                if (!data.equals(lastData)) {
                    lastData = data;
                    try (FileWriter fw = new FileWriter("clipboard_log.txt", true)) {
                        fw.write(LocalDateTime.now() + " -> " + data + "\n");
                    }
                    System.out.println("Copied: " + data);
                }
            } catch (Exception ignored) {}
            Thread.sleep(1000);
        }
    }
}
