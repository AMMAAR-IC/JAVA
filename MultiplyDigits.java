public class MultiplyDigits {

    public static void main(String[] args){

        int n=1234;
        int prod=1;

        while(n>0){
            prod*=n%10;
            n/=10;
        }

        System.out.println(prod);
    }
}
