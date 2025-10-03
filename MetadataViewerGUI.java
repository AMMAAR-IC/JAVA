// MetadataViewerGUI.java
import javax.swing.*;
import java.awt.*;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.*;

public class MetadataViewerGUI {
    public static void main(String[] args) throws Exception {
        JFrame f = new JFrame("Image Metadata Viewer");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea ta = new JTextArea(30,60);
        f.add(new JScrollPane(ta), BorderLayout.CENTER);

        JButton btn = new JButton("Open Image");
        btn.addActionListener(e -> {
            try {
                JFileChooser jc = new JFileChooser();
                if (jc.showOpenDialog(f) == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = jc.getSelectedFile();
                    Metadata md = ImageMetadataReader.readMetadata(file);
                    StringBuilder sb = new StringBuilder();
                    for (Directory d : md.getDirectories()) for (Tag t : d.getTags()) sb.append(t).append("\n");
                    ta.setText(sb.toString());
                }
            } catch (Exception ex) { ta.setText("Error: " + ex.getMessage()); }
        });
        f.add(btn, BorderLayout.NORTH);
        f.pack(); f.setVisible(true);
    }
}
