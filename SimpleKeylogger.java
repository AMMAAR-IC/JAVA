import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class SimpleKeylogger extends JFrame implements KeyListener {
    private StringBuilder log = new StringBuilder();

    public SimpleKeylogger() {
        setTitle("Keylogger");
        setSize(200, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        log.append(e.getKeyChar());
        System.out.println("Log: " + log);
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new SimpleKeylogger();
    }
}
