public class PrimeMelody {
    public static void main(String[] args) throws Exception {
        int count = 0;
        for (int i = 2; i < 200; i++) {
            if (isPrime(i)) {
                count++;
                String bar = "♪".repeat(count % 10 + 1);
                System.out.printf("%3d -> %s%n", i, bar);
                Thread.sleep(150);
            }
        }
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i=2; i*i<=n; i++) if (n%i==0) return false;
        return true;
    }
}
