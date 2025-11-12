import java.util.*;

public class NeuralOr {
    public static void main(String[] args) {
        double w1 = Math.random(), w2 = Math.random(), b = Math.random(), lr = 0.1;
        int[][] data = {{0,0,0},{0,1,1},{1,0,1},{1,1,1}};
        for (int e=0; e<1000; e++)
            for (int[] d : data) {
                double out = sigmoid(d[0]*w1 + d[1]*w2 + b);
                double err = d[2] - out;
                w1 += lr*err*d[0]; w2 += lr*err*d[1]; b += lr*err;
            }
        System.out.println("Predictions:");
        for (int[] d : data)
            System.out.printf("%d OR %d = %.2f%n", d[0], d[1], sigmoid(d[0]*w1 + d[1]*w2 + b));
    }
    static double sigmoid(double x){return 1/(1+Math.exp(-x));}
}
