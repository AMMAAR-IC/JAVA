import java.util.*;

public class MorseCode {
    private static final Map<Character, String> morseMap = new HashMap<>();
    static {
        morseMap.put('A', ".-"); morseMap.put('B', "-...");
        morseMap.put('C', "-.-."); morseMap.put('D', "-..");
        morseMap.put('E', "."); morseMap.put('F', "..-.");
        morseMap.put('G', "--."); morseMap.put('H', "....");
        morseMap.put('I', ".."); morseMap.put('J', ".---");
        morseMap.put('K', "-.-"); morseMap.put('L', ".-..");
        morseMap.put('M', "--"); morseMap.put('N', "-.");
        morseMap.put('O', "---"); morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-"); morseMap.put('R', ".-.");
        morseMap.put('S', "..."); morseMap.put('T', "-");
        morseMap.put('U', "..-"); morseMap.put('V', "...-");
        morseMap.put('W', ".--"); morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--"); morseMap.put('Z', "--..");
        morseMap.put('1', ".----"); morseMap.put('2', "..---");
        morseMap.put('3', "...--"); morseMap.put('4', "....-");
        morseMap.put('5', "....."); morseMap.put('6', "-....");
        morseMap.put('7', "--..."); morseMap.put('8', "---..");
        morseMap.put('9', "----."); morseMap.put('0', "-----");
    }

    public static String toMorse(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            if (morseMap.containsKey(c)) sb.append(morseMap.get(c)).append(" ");
        }
        return sb.toString();
    }

    public static void beepMorse(String morse) throws InterruptedException {
        for (char c : morse.toCharArray()) {
            if (c == '.') {
                java.awt.Toolkit.getDefaultToolkit().beep();
                Thread.sleep(200);
            } else if (c == '-') {
                java.awt.Toolkit.getDefaultToolkit().beep();
                Thread.sleep(600);
            }
            Thread.sleep(200);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = sc.nextLine();
        String morse = toMorse(input);
        System.out.println("Morse Code: " + morse);
        beepMorse(morse);
    }
}
