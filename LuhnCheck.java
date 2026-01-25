public class LuhnCheck {
    static boolean valid(String n){
        int sum = 0;
        boolean alt = false;
        for(int i=n.length()-1;i>=0;i--){
            int d = n.charAt(i)-'0';
            if(alt){
                d *= 2;
                if(d > 9) d -= 9;
            }
            sum += d;
            alt = !alt;
        }
        return sum % 10 == 0;
    }

    public static void main(String[] args){
        System.out.println(valid("4532015112830366"));
    }
}
