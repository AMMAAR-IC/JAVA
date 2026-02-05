public class MajorityElement {
    public static void main(String[] args){
        int[] a={2,2,1,2,3,2,2};
        int c=0, v=0;
        for(int x:a){
            if(c==0) v=x;
            c+=(x==v)?1:-1;
        }
        System.out.println(v);
    }
}
