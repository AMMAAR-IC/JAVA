// SnakeGame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    final int TILE = 20, WIDTH = 30, HEIGHT = 20;
    LinkedList<Point> snake = new LinkedList<>();
    Point food;
    int dirX = 1, dirY = 0;
    Timer timer = new Timer(100, this);

    public SnakeGame() {
        setPreferredSize(new Dimension(WIDTH*TILE, HEIGHT*TILE));
        setBackground(Color.BLACK);
        snake.add(new Point(WIDTH/2, HEIGHT/2));
        placeFood();
        timer.start();
        setFocusable(true); addKeyListener(this);
    }
    void placeFood() {
        Random r = new Random();
        food = new Point(r.nextInt(WIDTH), r.nextInt(HEIGHT));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        for (Point p: snake) g.fillRect(p.x*TILE, p.y*TILE, TILE, TILE);
        g.setColor(Color.RED); g.fillRect(food.x*TILE, food.y*TILE, TILE, TILE);
    }
    public void actionPerformed(ActionEvent e) {
        Point head = snake.peekFirst();
        Point next = new Point(head.x + dirX, head.y + dirY);
        if (next.x < 0 || next.x >= WIDTH || next.y < 0 || next.y >= HEIGHT || snake.contains(next)) {
            timer.stop(); JOptionPane.showMessage
