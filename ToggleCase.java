import java.util.*;

public class ToggleCase {

    public static void main(String[] args){

        String s="JavaCode";
        StringBuilder out=new StringBuilder();

        for(char c:s.toCharArray()){
            if(Character.isUpperCase(c))
                out.append(Character.toLowerCase(c));
            else
                out.append(Character.toUpperCase(c));
        }

        System.out.println(out);
    }
}
