import java.util.*;

public class MathExpr {

    public static void main(String[] args){

        Random r=new Random();

        int a=r.nextInt(10);
        int b=r.nextInt(10);

        char[] ops={'+','-','*','/'};
        char op=ops[r.nextInt(ops.length)];

        System.out.println(a+" "+op+" "+b);
    }
}
