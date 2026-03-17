public class ReverseEachWord {

    public static void main(String[] args){

        String s="Java is powerful";

        for(String w:s.split(" ")){
            System.out.print(new StringBuilder(w).reverse()+" ");
        }
    }
}
