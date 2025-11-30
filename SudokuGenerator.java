import java.util.*;

public class SudokuGenerator {
    static final int N = 9;
    static int[][] board = new int[N][N];
    static Random rnd = new Random();

    public static void main(String[] args) {
        fillDiagonal();
        fillRemaining(0, 3);
        int[][] puzzle = makePuzzle(40); // remove 40 cells
        print(puzzle);
    }

    static void fillDiagonal() {
        for (int i = 0; i < N; i += 3) fillBox(i, i);
    }

    static void fillBox(int row, int col) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board[row + r][col + c] = nums.remove(0);
    }

    static boolean safe(int r, int c, int num) {
        for (int x = 0; x < N; x++) if (board[r][x] == num || board[x][c] == num) return false;
        int sr = (r/3)*3, sc = (c/3)*3;
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) if (board[sr+i][sc+j]==num) return false;
        return true;
    }

    static boolean fillRemaining(int i, int j) {
        if (j >= N && i < N-1) { i++; j = 0; }
        if (i >= N) return true;
        if (board[i][j] != 0) return fillRemaining(i, j+1);

        for (int num = 1; num <= 9; num++) {
            int pick = rnd.nextInt(9) + 1;
            if (safe(i, j, pick)) {
                board[i][j] = pick;
                if (fillRemaining(i, j+1)) return true;
                board[i][j] = 0;
            }
        }
        // deterministic fallback
        for (int num = 1; num <= 9; num++)
            if (safe(i, j, num)) {
                board[i][j] = num;
                if (fillRemaining(i, j+1)) return true;
                board[i][j] = 0;
            }
        return false;
    }

    static int[][] makePuzzle(int removals) {
        int[][] p = new int[N][N];
        for (int r=0;r<N;r++) System.arraycopy(board[r],0,p[r],0,N);
        int removed=0;
        while (removed < removals) {
            int r = rnd.nextInt(N), c = rnd.nextInt(N);
            if (p[r][c] != 0) { p[r][c]=0; removed++; }
        }
        return p;
    }

    static void print(int[][] b) {
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) System.out.print((b[r][c]==0?".":b[r][c]) + " ");
            System.out.println();
        }
    }
}
