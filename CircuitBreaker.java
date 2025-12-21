public class CircuitBreaker {
    static int failures = 0;
    static boolean open = false;
    static long openedAt;

    static void call() throws Exception {
        if (open && System.currentTimeMillis() - openedAt < 3000)
            throw new Exception("Circuit open");

        try {
            if (Math.random() < 0.7) throw new Exception("Fail");
            failures = 0;
            open = false;
            System.out.println("Success");
        } catch (Exception e) {
            failures++;
            if (failures >= 3) {
                open = true;
                openedAt = System.currentTimeMillis();
            }
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            try { call(); }
            catch (Exception e) { System.out.println(e.getMessage()); }
            Thread.sleep(500);
        }
    }
}
