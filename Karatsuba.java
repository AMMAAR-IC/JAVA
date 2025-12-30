public class Karatsuba {
    public static void main(String[] args){
        System.out.println(mul("123456","789012"));
    }

    static String mul(String a,String b){
        if(a.length()==1||b.length()==1) return Integer.parseInt(a)*Integer.parseInt(b)+"";

        int n=Math.max(a.length(),b.length());
        int half=n/2;

        a=pad(a,n); b=pad(b,n);

        String a1=a.substring(0,n-half), a2=a.substring(n-half);
        String b1=b.substring(0,n-half), b2=b.substring(n-half);

        int p1=Integer.parseInt(mul(a1,b1));
        int p2=Integer.parseInt(mul(a2,b2));
        int p3=Integer.parseInt(mul(add(a1,a2),add(b1,b2))) - p1 - p2;

        return p1*(int)Math.pow(10,2*half) + p3*(int)Math.pow(10,half) + p2 + "";
    }

    static String pad(String s,int n){ return "0".repeat(n-s.length())+s; }
    static String add(String a,String b){ return Integer.parseInt(a)+Integer.parseInt(b)+""; }
}
