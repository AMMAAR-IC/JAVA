public class Base91 {
    static final String T = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "!#$%&()*+,./:;<=>?@[]^_`{|}~\"";

    public static void main(String[] args) {
        byte[] data = "Hello Ammaar".getBytes();
        System.out.println(encode(data));
    }

    static String encode(byte[] d){
        StringBuilder out = new StringBuilder();
        int b = 0, n = 0;

        for (byte x : d) {
            b |= (x & 255) << n;
            n += 8;
            if (n > 13) {
                int v = b & 8191;
                if (v > 88) {
                    b >>= 13; n -= 13;
                } else {
                    v = b & 16383;
                    b >>= 14; n -= 14;
                }
                out.append(T.charAt(v % 91)).append(T.charAt(v / 91));
            }
        }

        if (n > 0) {
            out.append(T.charAt(b % 91));
            if (n > 7 || b > 90) out.append(T.charAt(b / 91));
        }
        return out.toString();
    }
}
