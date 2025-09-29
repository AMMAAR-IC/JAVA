// ProgressBar.java
public class ProgressBar {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i <= 100; i++) {
            String bar = "[" + "=".repeat(i / 2) + " ".repeat(50 - i / 2) + "]";
            System.out.print("\r" + bar + " " + i + "%");
            Thread.sleep(50);
        }
        System.out.println("\nDone!");
    }
}
