public class MatrixMultiplication {
    public static void main(String[] args) {
        int[][] A = {{1,2,3},{4,5,6}};
        int[][] B = {{7,8},{9,10},{11,12}};
        int[][] C = new int[A.length][B[0].length];

        for (int i=0;i<A.length;i++) {
            for (int j=0;j<B[0].length;j++) {
                for (int k=0;k<B.length;k++) {
                    C[i][j] += A[i][k]*B[k][j];
                }
            }
        }

        for (int[] row : C) {
            for (int val : row) System.out.print(val + " ");
            System.out.println();
        }
    }
}
