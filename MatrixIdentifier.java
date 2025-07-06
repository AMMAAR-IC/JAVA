import java.util.*;

public class MatrixAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter matrix size (n x n): ");
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];

        System.out.println("Enter matrix values:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = sc.nextInt();

        System.out.println("\n🔍 Matrix Properties:");
        if (isSymmetric(matrix)) System.out.println("✔ Symmetric Matrix");
        else System.out.println("❌ Not Symmetric");

        if (isMagicSquare(matrix)) System.out.println("✔ Magic Square");
        else System.out.println("❌ Not a Magic Square");

        if (isIdentity(matrix)) System.out.println("✔ Identity Matrix");
        else System.out.println("❌ Not an Identity Matrix");
    }

    static boolean isSymmetric(int[][] m) {
        int n = m.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (m[i][j] != m[j][i])
                    return false;
        return true;
    }

    static boolean isMagicSquare(int[][] m) {
        int n = m.length;
        int sum = 0;
        for (int j = 0; j < n; j++) sum += m[0][j];

        for (int i = 1; i < n; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++)
                rowSum += m[i][j];
            if (rowSum != sum) return false;
        }

        for (int j = 0; j < n; j++) {
            int colSum = 0;
            for (int i = 0; i < n; i++)
                colSum += m[i][j];
            if (colSum != sum) return false;
        }

        int d1 = 0, d2 = 0;
        for (int i = 0; i < n; i++) {
            d1 += m[i][i];
            d2 += m[i][n - 1 - i];
        }
        return (d1 == sum && d2 == sum);
    }

    static boolean isIdentity(int[][] m) {
        int n = m.length;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == j && m[i][j] != 1) return false;
                if (i != j && m[i][j] != 0) return false;
            }
        return true;
    }
}
