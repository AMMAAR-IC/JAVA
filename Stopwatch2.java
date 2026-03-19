public class Stopwatch2 {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        Thread.sleep(2000);

        long end = System.currentTimeMillis();

        System.out.println("Time: " + (end-start) + " ms");
    }
}
