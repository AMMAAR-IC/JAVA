public class TypingEffect {
    public static void main(String[] args) throws InterruptedException {
        String msg = "⚡ Welcome to the Typing Effect in Java ⚡";
        for (char c : msg.toCharArray()) {
            System.out.print(c);
            Thread.sleep(120);
        }
        System.out.println();
    }
}
