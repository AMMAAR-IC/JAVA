// PaintApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintApp extends JPanel {
    private int x, y, prevX, prevY;

    public PaintApp() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { prevX = e.getX(); prevY = e.getY(); }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                x = e.getX(); y = e.getY();
                getGraphics().drawLine(prevX, prevY, x, y);
                prevX = x; prevY = y;
            }
        });
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Paint App");
        f.add(new PaintApp());
        f.setSize(500, 500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
