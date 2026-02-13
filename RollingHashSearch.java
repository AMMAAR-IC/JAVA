public class RollingHashSearch {
    static long hash(String s){
        long h=0;
        for(char c:s.toCharArray()) h=h*31+c;
        return h;
    }

    public static void main(String[] args){
        String text="this is java and java is powerful";
        String pat="java";

        long ph=hash(pat);
        for(int i=0;i<=text.length()-pat.length();i++){
            if(hash(text.substring(i,i+pat.length()))==ph)
                System.out.println("Found at " + i);
        }
    }
}
