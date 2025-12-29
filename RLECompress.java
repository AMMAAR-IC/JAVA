public class RLECompress {
    public static void main(String[] args){
        String s="aaaabbccccdde";
        StringBuilder o=new StringBuilder();
        for(int i=0;i<s.length();){
            char c=s.charAt(i);
            int count=0;
            while(i<s.length() && s.charAt(i)==c){ count++; i++; }
            o.append(c).append(count);
        }
        System.out.println(o);
    }
}
