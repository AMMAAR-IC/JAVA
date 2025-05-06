package GAME.java;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

class frame extends JFrame{
    JLabel label = new JLabel();
    frame(){
        label.setBounds(700,350,500,100);
        label.setBackground(new Color(0, 0, 0));
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("ARIAL", Font.PLAIN, 40));
        label.setOpaque(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(1800, 800);
        this.getContentPane().setBackground(new Color(0, 0, 0));
        this.add(label);
    }
}
class game implements KeyListener {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    int speed = 30;

    game() {
        label.setBounds(1000, 100, 100, 100);
        label.setBackground(Color.GRAY);
        label.setOpaque(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(1800, 800);
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.add(label);
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 1:
                frame.dispose();
        }
        int x = label.getX();
        int y = label.getY();
        int width = label.getWidth();
        int height = label.getHeight();
        int frameWidth = frame.getWidth()-10;
        int frameHeight = frame.getHeight()-10;

        switch (e.getKeyCode()) {
            case 38:
                if (y - speed >= 0) {
                    label.setLocation(x, y - speed);
                }
                break;
            case 37:
                if (x - speed >= 0) {
                    label.setLocation(x - speed, y);
                }
                break;
            case 40:
                if (y + height + speed <= frameHeight){
                    label.setLocation(x, y + speed);
                }
                break;
            case 39:
                if (x + width + speed <= frameWidth) {
                    label.setLocation(x + speed, y);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

class TIME extends frame{
    TIME(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int count = 0;
            int i = 0;

            @Override
            public void run() {
                switch (count) {
                    case 1:
                        label.setText("h");
                        break;
                    case 2:
                        label.setText("he");
                        break;
                    case 3:
                        label.setText("hel");
                        break;
                    case 4:
                        label.setText("hell");
                        break;
                    case 5:
                        label.setText("hello");
                        break;
                    case 6:
                        label.setText("");
                        break;
                    case 7:
                        label.setText("I");
                        break;
                    case 8:
                        label.setText("I ");
                        break;
                    case 9:
                        label.setText("I a");
                        break;
                    case 10:
                        label.setText("I am");
                        break;
                    case 11:
                        label.setText("I am ");
                        break;
                    case 12:
                        label.setText("I am R");
                        break;
                    case 13:
                        label.setText("I am R2");
                        break;
                    case 14:
                        label.setText("I am R25");
                        break;
                    case 15:
                        label.setText("I am R252");
                        break;
                    case 16:
                        label.setText("I am R2524");
                        break;
                    case 17:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("");
                        break;
                    case 18:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("a");
                        break;
                    case 19:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("an");
                        break;
                    case 20:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and");
                        break;
                    case 21:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and ");
                        break;
                    case 22:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and y");
                        break;
                    case 23:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and yo");
                        break;
                    case 24:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and you");
                        break;
                    case 25:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and you ");
                        break;
                    case 26:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and you a");
                        break;
                    case 27:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and you ar");
                        break;
                    case 28:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and you are");
                        break;
                    case 29:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("and you are ");
                        break;
                    case 30:
                        label.setBounds(600, 350, 500, 100);
                        label.setText(" ");
                        break;
                    case 31:
                        label.setBounds(700,350,500,100);
                        label.setText("n");
                        break;
                    case 32:
                        label.setBounds(700,350,500,100);
                        label.setText("no");
                        break;
                    case 33:
                        label.setBounds(700,350,500,100);
                        label.setText("now");
                        break;
                    case 34:
                        label.setBounds(700,350,500,100);
                        label.setText(" ");
                        break;
                    case 35:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("p");
                        break;
                    case 36:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("pl");
                        break;
                    case 37:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("pla");
                        break;
                    case 38:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("play");
                        break;
                    case 39:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playi");
                        break;
                    case 40:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playin");
                        break;
                    case 41:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing");
                        break;
                    case 42:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing ");
                        break;
                    case 43:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing t");
                        break;
                    case 44:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing th");
                        break;
                    case 45:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the");
                        break;
                    case 46:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the ");
                        break;
                    case 47:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the C");
                        break;
                    case 48:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the CA");
                        break;
                    case 49:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the CAS");
                        break;
                    case 50:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the CASE");
                        break;
                    case 51:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the CASE ");
                        break;
                    case 52:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the CASE 5");
                        break;
                    case 53:
                        label.setBounds(600, 350, 500, 100);
                        label.setText("playing the CASE 51");
                        break;
                    case 54:
                        dispose(); // Close the current frame
                        new game(); // Create a new Game
                        break;
                    default:
                        break;
                }
                i++;
                count++;
            }
        };
        timer.schedule(task,0,250);
    }
}

public class Main {
    public static void main(String[] args) {
        new frame();
        new TIME();
    }
}