import java.util.*;

public class TicTacToeAI {
    static char[] board = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
    static char human = 'O', ai = 'X';

    static int bestMove() {
        int bestScore = Integer.MIN_VALUE, move = -1;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = ai;
                int score = minimax(false);
                board[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        return move;
    }

    static int minimax(boolean isMax) {
        Character result = checkWinner();
        if (result != null) {
            if (result == ai) return 1;
            else if (result == human) return -1;
            else return 0;
        }
        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                board[i] = isMax ? ai : human;
                int score = minimax(!isMax);
                board[i] = ' ';
                best = isMax ? Math.max(best, score) : Math.min(best, score);
            }
        }
        return best;
    }

    static Character checkWinner() {
        int[][] wins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] w : wins) {
            if (board[w[0]]!=' ' && board[w[0]]==board[w[1]] && board[w[1]]==board[w[2]])
                return board[w[0]];
        }
        for (char c : board) if (c==' ') return null;
        return 'D'; // Draw
    }

    static void printBoard() {
        for (int i=0;i<9;i++) {
            System.out.print(board[i]==' ' ? (i+1) : board[i]);
            if (i%3==2) System.out.println();
            else System.out.print(" | ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.print("Your move (1-9): ");
            int move = sc.nextInt()-1;
            if (board[move]!=' ') continue;
            board[move] = human;
            if (checkWinner()!=null) break;
            board[bestMove()] = ai;
            if (checkWinner()!=null) break;
        }
        printBoard();
        Character res = checkWinner();
        System.out.println(res=='D' ? "It's a Draw!" : res+" wins!");
    }
}
