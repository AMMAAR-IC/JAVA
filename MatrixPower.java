import java.util.*;

public class MatrixPower {
    static long MOD = 1_000_000_007L;
    public static void main(String[] args) {
        long[][] A = {{1,1},{1,0}};
        long n = 50;
        long[][] r = pow(A, n);
        for (int i=0;i<r.length;i++) System.out.println(Arrays.toString(r[i]));
    }

    static long[][] mul(long[][] a, long[][] b) {
        int n=a.length; int m=b[0].length; int p=b.length;
        long[][] c = new long[n][m];
        for (int i=0;i<n;i++) for (int k=0;k<p;k++) for (int j=0;j<m;j++)
            c[i][j] = (c[i][j] + a[i][k]*b[k][j])%MOD;
        return c;
    }

    static long[][] pow(long[][] a, long e) {
        int n = a.length;
        long[][] res = new long[n][n];
        for (int i=0;i<n;i++) res[i][i]=1;
        while (e>0) {
            if ((e&1)==1) res = mul(res, a);
            a = mul(a, a); e >>= 1;
        }
        return res;
    }
}
