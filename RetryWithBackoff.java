public class RetryWithBackoff {
    static void call() throws Exception {
        if (Math.random() < 0.8) throw new Exception("Fail");
        System.out.println("Success");
    }

    public static void main(String[] args) throws Exception {
        int retries = 5;
        long delay = 500;

        for (int i = 0; i < retries; i++) {
            try {
                call();
                return;
            } catch (Exception e) {
                Thread.sleep(delay);
                delay *= 2;
            }
        }
        System.out.println("All retries failed");
    }
}
