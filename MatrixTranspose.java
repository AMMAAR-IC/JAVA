public class MatrixTranspose {
    public static void main(String[] args) {
        int[][] mat = { {1,2,3}, {4,5,6} };
        int[][] trans = new int[mat[0].length][mat.length];
        for(int i=0;i<mat.length;i++)
            for(int j=0;j<mat[0].length;j++)
                trans[j][i]=mat[i][j];
        for(int[] row: trans) {
            for(int x: row) System.out.print(x+" ");
            System.out.println();
        }
    }
}
