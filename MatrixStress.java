public class MatrixStress {
    public static void main(String[] args) {
        int size = 300;
        double[][] a = new double[size][size];
        double[][] b = new double[size][size];
        double[][] c = new double[size][size];

        for (int i=0;i<size;i++)
            for (int j=0;j<size;j++)
                a[i][j] = b[i][j] = Math.random();

        System.out.println("Starting stress test...");
        while (true) {
            for (int i=0;i<size;i++)
                for (int j=0;j<size;j++) {
                    c[i][j] = 0;
                    for (int k=0;k<size;k++)
                        c[i][j] += a[i][k] * b[k][j];
                }
        }
    }
}
