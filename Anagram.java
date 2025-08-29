import java.util.*;

public class Anagram {
    public static void main(String[] args) {
        String a = "listen", b = "silent";
        char[] x = a.toCharArray(), y = b.toCharArray();
        Arrays.sort(x); Arrays.sort(y);
        System.out.println(a+" & "+b+" → "+Arrays.equals(x,y));
    }
}
