public class ManualDivide {
    public static void main(String[] args) {
        String a = "987654321987654321987654321";
        String b = "123456789";
        System.out.println(divide(a,b));
    }

    static String divide(String A, String B){
        StringBuilder q = new StringBuilder();
        long divisor = Long.parseLong(B);
        long cur = 0;

        for (char c : A.toCharArray()) {
            cur = cur * 10 + (c - '0');
            q.append(cur / divisor);
            cur %= divisor;
        }
        return q.toString().replaceFirst("^0+(?!$)", "");
    }
}
