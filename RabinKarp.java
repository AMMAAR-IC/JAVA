public class RabinKarp {
    static int search(String t,String p){
        int n=t.length(), m=p.length();
        long ph=0, th=0, h=1, q=101;

        for(int i=0;i<m-1;i++) h=(h*256)%q;
        for(int i=0;i<m;i++){
            ph=(256*ph+p.charAt(i))%q;
            th=(256*th+t.charAt(i))%q;
        }

        for(int i=0;i<=n-m;i++){
            if(ph==th && t.substring(i,i+m).equals(p))
                return i;
            if(i<n-m){
                th=(256*(th-t.charAt(i)*h)+t.charAt(i+m))%q;
                if(th<0) th+=q;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        System.out.println(search("ammaarjava","java"));
    }
}
