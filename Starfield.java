import java.util.Random;

public class Starfield {
    static final int WIDTH = 80, HEIGHT = 20, STARS = 50;
    static int[] starX = new int[STARS], starY = new int[STARS];

    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        for (int i = 0; i < STARS; i++) {
            starX[i] = r.nextInt(WIDTH);
            starY[i] = r.nextInt(HEIGHT);
        }

        while (true) {
            char[][] screen = new char[HEIGHT][WIDTH];
            for (int y = 0; y < HEIGHT; y++) 
                for (int x = 0; x < WIDTH; x++) 
                    screen[y][x] = ' ';

            for (int i = 0; i < STARS; i++) {
                screen[starY[i]][starX[i]] = '*';
                starX[i]++;
                if (starX[i] >= WIDTH) {
                    starX[i] = 0;
                    starY[i] = r.nextInt(HEIGHT);
                }
            }

            System.out.print("\033[H\033[2J"); // Clear screen
            System.out.flush();
            for (char[] row : screen) System.out.println(row);
            Thread.sleep(100);
        }
    }
}
