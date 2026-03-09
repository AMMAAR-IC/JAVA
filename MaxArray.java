public class MaxArray {

    public static void main(String[] args){

        int[] a={10,45,22,99,3};

        int max=a[0];

        for(int n:a)
            if(n>max)
                max=n;

        System.out.println(max);
    }
}
