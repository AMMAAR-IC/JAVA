public class DFT {
    public static void main(String[] args){
        double[] signal = {1,0,1,0,1,0,1,0};
        double[] out = dft(signal);
        for (double v : out) System.out.println(v);
    }

    static double[] dft(double[] x){
        int N = x.length;
        double[] mag = new double[N];

        for (int k=0;k<N;k++){
            double re=0, im=0;
            for (int n=0;n<N;n++){
                double angle = -2*Math.PI*k*n/N;
                re += x[n]*Math.cos(angle);
                im += x[n]*Math.sin(angle);
            }
            mag[k] = Math.sqrt(re*re + im*im);
        }
        return mag;
    }
}
