public class RetryExecutor {
    static void run(Runnable r, int tries) throws Exception {
        for(int i=0;i<tries;i++){
            try { r.run(); return; }
            catch(Exception e){ Thread.sleep(500); }
        }
        System.out.println("Failed");
    }

    public static void main(String[] args) throws Exception {
        run(() -> { throw new RuntimeException(); }, 3);
    }
}
