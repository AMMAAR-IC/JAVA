import java.util.*;

public class EmailGen {

    static String[] domains = {"gmail.com","yahoo.com","outlook.com"};

    public static void main(String[] args){

        Random r = new Random();

        String user = "user"+r.nextInt(1000);

        System.out.println(user+"@"+domains[r.nextInt(domains.length)]);
    }
}
