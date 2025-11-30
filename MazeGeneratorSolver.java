import java.util.*;

public class MazeGeneratorSolver {
    static final int W=21,H=21; // odd dimensions
    static int[][] maze = new int[H][W]; // 0 wall 1 path
    static Random rnd = new Random();

    public static void main(String[] args) {
        generate();
        print();
        boolean solved = solve(1,1, new boolean[H][W]);
        System.out.println("Solved: " + solved);
    }

    static void generate() {
        for (int y=0;y<H;y++) for (int x=0;x<W;x++) maze[y][x]=0;
        carve(1,1);
    }

    static void carve(int x, int y) {
        maze[y][x]=1;
        int[] dirs = {0,1,2,3};
        for (int i=0;i<4;i++) { int j = rnd.nextInt(4); int t=dirs[i]; dirs[i]=dirs[j]; dirs[j]=t; }
        for (int d: dirs) {
            int nx = x + (d==0?2:d==1?0:d==2?-2:0);
            int ny = y + (d==0?0:d==1?2:d==2?0:-2);
            if (nx>0 && nx<W-1 && ny>0 && ny<H-1 && maze[ny][nx]==0) {
                maze[y + (ny-y)/2][x + (nx-x)/2] = 1;
                carve(nx, ny);
            }
        }
    }

    static boolean solve(int x,int y, boolean[][] vis) {
        if (x==W-2 && y==H-2) { maze[y][x]=2; return true; }
        vis[y][x]=true; maze[y][x]=2;
        int[][] ds = {{1,0},{0,1},{-1,0},{0,-1}};
        for (var d: ds) {
            int nx=x+d[0], ny=y+d[1];
            if (nx>0&&ny>0&&nx<W&&ny<H && maze[ny][nx]==1 && !vis[ny][nx]) if (solve(nx,ny,vis)) return true;
        }
        maze[y][x]=1; return false;
    }

    static void print() {
        for (int y=0;y<H;y++) {
            for (int x=0;x<W;x++) {
                char ch = maze[y][x]==0?'█': maze[y][x]==2?'.':' ';
                System.out.print(ch);
            }
            System.out.println();
        }
    }
}
