public class EndVowel {

    public static void main(String[] args){

        String s="java code data echo alpha";

        int count=0;

        for(String w:s.split(" "))
            if("aeiou".indexOf(w.charAt(w.length()-1))>=0)
                count++;

        System.out.println(count);
    }
}
