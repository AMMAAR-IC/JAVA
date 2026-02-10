public class KMP {
    static int search(String t,String p){
        int[] lps=new int[p.length()];
        for(int i=1,len=0;i<p.length();){
            if(p.charAt(i)==p.charAt(len)) lps[i++]=++len;
            else if(len>0) len=lps[len-1];
            else lps[i++]=0;
        }

        for(int i=0,j=0;i<t.length();){
            if(t.charAt(i)==p.charAt(j)){ i++; j++; }
            if(j==p.length()) return i-j;
            if(i<t.length() && t.charAt(i)!=p.charAt(j))
                if(j!=0) j=lps[j-1]; else i++;
        }
        return -1;
    }

    public static void main(String[] args){
        System.out.println(search("ammaar loves java","java"));
    }
}
