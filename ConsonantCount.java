public class ConsonantCount {

    public static void main(String[] args){

        String s="Java Programming";

        int count=0;

        for(char c:s.toLowerCase().toCharArray())
            if(Character.isLetter(c) && "aeiou".indexOf(c)<0)
                count++;

        System.out.println(count);
    }
}
