public class VowelWords {

    public static void main(String[] args){

        String s="apple banana orange umbrella dog";

        int count=0;

        for(String w : s.split(" "))
            if("aeiou".indexOf(w.charAt(0))>=0)
                count++;

        System.out.println(count);
    }
}
