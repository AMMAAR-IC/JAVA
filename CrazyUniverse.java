import java.util.*;

public class CrazyUniverse {

    // ANSI Colors
    static final String RESET  = "\u001B[0m";
    static final String RED    = "\u001B[31m";
    static final String GREEN  = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE   = "\u001B[34m";
    static final String PURPLE = "\u001B[35m";
    static final String CYAN   = "\u001B[36m";
    static final String WHITE  = "\u001B[97m";
    static final String ORANGE = "\u001B[38;5;208m";
    static final String PINK   = "\u001B[38;5;213m";

    static final String[] ALL_COLORS = {RED, GREEN, YELLOW, BLUE, PURPLE, CYAN, WHITE, ORANGE, PINK};

    static final int W = 100, H = 30;
    static final Random rand = new Random();

    // ─── Entity Types ───────────────────────────────────────────────
    static class Particle {
        double x, y, vx, vy;
        char symbol;
        String color;
        int life;

        Particle(double x, double y, double vx, double vy, char sym, String col, int life) {
            this.x = x; this.y = y; this.vx = vx; this.vy = vy;
            this.symbol = sym; this.color = col; this.life = life;
        }
    }

    static class BlackHole {
        double x, y;
        int size;
        int pulseTimer;

        BlackHole(double x, double y) {
            this.x = x; this.y = y;
            this.size = 2 + rand.nextInt(3);
            this.pulseTimer = 0;
        }
    }

    static class Rocket {
        double x, y, vx, vy;
        String color;
        int fuel;
        boolean exploded;

        Rocket(double x, double y) {
            this.x = x; this.y = y;
            this.vx = (rand.nextDouble() - 0.5) * 4;
            this.vy = -(1 + rand.nextDouble() * 3);
            this.color = ALL_COLORS[rand.nextInt(ALL_COLORS.length)];
            this.fuel = 15 + rand.nextInt(20);
            this.exploded = false;
        }
    }

    static class Star {
        double x, y;
        int twinkleTimer;
        char[] shapes = {'.', '+', '*', '·'};
        int shapeIdx;

        Star(double x, double y) {
            this.x = x; this.y = y;
            this.twinkleTimer = rand.nextInt(20);
            this.shapeIdx = ran
