public class PrimeSpiral {
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        int size = 15; // spiral grid size
        int[][] grid = new int[size][size];
        int x = size / 2, y = size / 2;
        int num = 1, steps = 1;

        while (num <= size * size) {
            for (int dir = 0; dir < 4; dir++) {
                for (int i = 0; i < steps; i++) {
                    if (x >= 0 && x < size && y >= 0 && y < size)
                        grid[y][x] = num++;
                    if (dir == 0) x++;
                    else if (dir == 1) y++;
                    else if (dir == 2) x--;
                    else y--;
                }
                if (dir % 2 == 1) steps++;
            }
        }

        for (int[] row : grid) {
            for (int n : row) {
                if (isPrime(n)) System.out.print(" * ");
                else System.out.print(" . ");
            }
            System.out.println();
        }
    }
}
