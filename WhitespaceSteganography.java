public class WhitespaceSteganography {
    public static void main(String[] args) {
        String message = "Secret123";
        String encoded = encode(message);
        System.out.println("Encoded (invisible):");
        System.out.println(encoded);

        System.out.println("\nDecoded:");
        System.out.println(decode(encoded));
    }

    static String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char b : binary.toCharArray()) {
                encoded.append(b == '0' ? ' ' : '\t');
            }
        }
        return encoded.toString();
    }

    static String decode(String ws) {
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < ws.length(); i += 8) {
            String bin = "";
            for (int j = 0; j < 8 && i + j < ws.length(); j++) {
                bin += (ws.charAt(i + j) == ' ') ? '0' : '1';
            }
            decoded.append((char) Integer.parseInt(bin, 2));
        }
        return decoded.toString();
    }
}
