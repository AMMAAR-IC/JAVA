import java.util.*;

public class MazeSolver {

    static class Point {
        int x, y, dist;
        Point(int x, int y, int dist) {
            this.x = x; this.y = y; this.dist = dist;
        }
    }

    public static int shortestPath(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Point> q = new LinkedList<>();
        if (maze[0][0] == 1) q.add(new Point(0, 0, 1));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            if (p.x == n - 1 && p.y == m - 1) return p.dist;

            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d], ny = p.y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m &&
                    maze[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, p.dist + 1));
                }
            }
        }

        return -1; // No path found
    }

    public static void main(String[] args) {
        int[][] maze = {
            {1, 0, 1, 1},
            {1, 1, 1, 0},
            {0, 1, 0, 1},
            {1, 1, 1, 1}
        };

        int result = shortestPath(maze);
        if (result != -1)
            System.out.println("✅ Shortest Path Length = " + result);
        else
            System.out.println("❌ No path found.");
    }
}
