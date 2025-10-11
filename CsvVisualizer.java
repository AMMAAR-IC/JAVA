// CsvVisualizer.java
import javax.swing.*;
import java.awt.*;
import java.nio.file.*;
import java.util.*;

public class CsvVisualizer extends JPanel {
    private Map<String, Integer> data = new LinkedHashMap<>();

    public CsvVisualizer() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("data.csv"));
        for (String l : lines) {
            String[] parts = l.split(",");
            data.put(parts[0], Integer.parseInt(parts[1]));
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        for (var entry : data.entrySet()) {
            g.drawString(entry.getKey(), x, 400);
            g.fillRect(x, 400 - entry.getValue(), 40, entry.getValue());
            x += 60;
        }
    }

    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("CSV Visualizer");
        f.add(new CsvVisualizer());
        f.setSize(600, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
