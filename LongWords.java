public class LongWords {

    public static void main(String[] args){

        String s="Java programming is very powerful";

        int count=0;

        for(String w:s.split(" "))
            if(w.length()>5)
                count++;

        System.out.println(count);
    }
}
