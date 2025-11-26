// GameOfLifeCLI.java
import java.util.*;
public class GameOfLifeCLI {
    static int W=80,H=24;
    static boolean[][] g;
    public static void main(String[] args) throws Exception {
        g = new boolean[H][W];
        Random r = new Random();
        for (int y=0;y<H;y++) for (int x=0;x<W;x++) g[y][x] = r.nextDouble() < 0.15;
        while (true) {
            render();
            g = step(g);
            Thread.sleep(150);
        }
    }
    static boolean[][] step(boolean[][] cur){
        boolean[][] next = new boolean[H][W];
        for (int y=0;y<H;y++) for (int x=0;x<W;x++){
            int n=0;
            for (int dy=-1;dy<=1;dy++) for (int dx=-1;dx<=1;dx++){
                if (dy==0 && dx==0) continue;
                int ny=(y+dy+H)%H, nx=(x+dx+W)%W;
                if (cur[ny][nx]) n++;
            }
            next[y][x] = cur[y][x] ? (n==2 || n==3) : (n==3);
        }
        return next;
    }
    static void render(){
        StringBuilder sb = new StringBuilder("\u001b[H"); // move cursor home
        for (int y=0;y<H;y++){
            for (int x=0;x<W;x++) sb.append(g[y][x] ? "█" : " ");
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
