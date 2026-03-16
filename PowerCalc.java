public class PowerCalc {

    public static void main(String[] args){

        int base = 2;
        int exp = 5;

        int result = 1;

        for(int i=0;i<exp;i++)
            result *= base;

        System.out.println(result);
    }
}
