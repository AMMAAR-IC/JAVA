import java.util.*;

public class QuoteCLI {

    static String[] quotes={
            "Code never lies.",
            "Java runs everywhere.",
            "Debugging is detective work."
    };

    public static void main(String[] args){

        Random r=new Random();

        System.out.println(quotes[r.nextInt(quotes.length)]);
    }
}
