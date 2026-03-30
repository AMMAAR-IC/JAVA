public class DivisibleCount {

    public static void main(String[] args){

        int[] arr={10,15,20,25,30};
        int n=5;

        int count=0;

        for(int x:arr)
            if(x%n==0)
                count++;

        System.out.println(count);
    }
}
