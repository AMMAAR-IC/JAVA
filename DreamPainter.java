import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DreamPainter extends JPanel {
    private final int size = 800, cellSize = 4, cols = size / cellSize, rows = size / cellSize;
    private final Color[][] grid = new Color[cols][rows];
    private final Random rand = new Random();

    public DreamPainter() {
        setPreferredSize(new Dimension(size, size));
        initializeGrid();
        Timer timer = new Timer(100, e -> {
            evolve();
            repaint();
        });
        timer.start();
    }

    private void initializeGrid() {
        for (int x = 0; x < cols; x++)
            for (int y = 0; y < rows; y++)
                grid[x][y] = rand.nextDouble() < 0.2 ? randomColor() : Color.BLACK;
    }

    private Color randomColor() {
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    private void evolve() {
        Color[][] next = new Color[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                int neighbors = countColoredNeighbors(x, y);
                if (neighbors == 3)
                    next[x][y] = randomColor();
                else if (neighbors == 2)
                    next[x][y] = grid[x][y];
                else
                    next[x][y] = Color.BLACK;
            }
        }
        System.arraycopy(next, 0, grid, 0, grid.length);
    }

    private int countColoredNeighbors(int x, int y) {
        int count = 0;
        for (int dx = -1; dx <= 1; dx++)
            for (int dy = -1; dy <= 1; dy++)
                if (!(dx == 0 && dy == 0) && isAlive(x + dx, y + dy))
                    count++;
        return count;
    }

    private boolean isAlive(int x, int y) {
        x = (x + cols) % cols;
        y = (y + rows) % rows;
        return grid[x][y] != Color.BLACK;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int x = 0; x < cols; x++)
            for (int y = 0; y < rows; y++) {
                g.setColor(grid[x][y]);
                g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dream Painter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DreamPainter());
        frame.pack();
        frame.setVisible(true);
    }
}
