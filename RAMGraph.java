public class RAMGraph {
    public static void main(String[] args) throws Exception {
        while (true) {
            long free = Runtime.getRuntime().freeMemory() / (1024*1024);
            long total = Runtime.getRuntime().maxMemory() / (1024*1024);
            long used = total - free;
            int bars = (int)((used * 50.0) / total);

            System.out.printf("RAM: %dMB / %dMB | ", used, total);
            System.out.println("#".repeat(bars) + "-".repeat(50-bars));
            Thread.sleep(1000);
        }
    }
}
