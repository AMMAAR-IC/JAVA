import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class KeyLogger extends JFrame implements KeyListener {
    public KeyLogger() {
        addKeyListener(this);
        setSize(300, 200);
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + e.getKeyChar());
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new KeyLogger();
    }
}
