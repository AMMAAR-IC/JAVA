public class SumDigitsString {

    public static void main(String[] args){

        String num="12345";

        int sum=0;

        for(char c:num.toCharArray())
            sum+=c-'0';

        System.out.println(sum);
    }
}
