public class FixedPointMath {
    static final int SCALE = 1 << 16;

    static int toFixed(double x){ return (int)(x * SCALE); }
    static double toDouble(int x){ return x / (double)SCALE; }

    static int mul(int a, int b){ return (int)(((long)a * b) >> 16); }
    static int div(int a, int b){ return (int)(((long)a << 16) / b); }

    public static void main(String[] args){
        int A = toFixed(3.14159);
        int B = toFixed(2.71828);

        System.out.println("Mul = " + toDouble(mul(A,B)));
        System.out.println("Div = " + toDouble(div(A,B)));
    }
}
