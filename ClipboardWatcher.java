// ClipboardWatcher.java
import java.awt.*;
import java.awt.datatransfer.*;

public class ClipboardWatcher {
    public static void main(String[] args) throws Exception {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        String last = "";
        while (true) {
            try {
                String data = (String) cb.getData(DataFlavor.stringFlavor);
                if (!data.equals(last)) {
                    System.out.println("Copied: " + data);
                    last = data;
                }
            } catch (Exception ignored) {}
            Thread.sleep(500);
        }
    }
}
