public class BaseNEncoder {
    static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static String encode(long n, int base){
        StringBuilder s = new StringBuilder();
        while(n > 0){
            s.append(chars.charAt((int)(n % base)));
            n /= base;
        }
        return s.reverse().toString();
    }

    public static void main(String[] args){
        System.out.println(encode(999999, 36));
    }
}
