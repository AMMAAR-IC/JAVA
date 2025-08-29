import java.util.*;

public class ExpressionEval {
    static int prec(char c){ return (c=='+'||c=='-')?1:(c=='*'||c=='/')?2:-1; }

    static String toPostfix(String exp){
        StringBuilder res=new StringBuilder();
        Stack<Character> st=new Stack<>();
        for(char c:exp.toCharArray()){
            if(Character.isLetterOrDigit(c)) res.append(c);
            else if(c=='(') st.push(c);
            else if(c==')'){
                while(!st.isEmpty() && st.peek()!='(') res.append(st.pop());
                st.pop();
            } else {
                while(!st.isEmpty() && prec(c)<=prec(st.peek())) res.append(st.pop());
                st.push(c);
            }
        }
        while(!st.isEmpty()) res.append(st.pop());
        return res.toString();
    }

    static int eval(String post){
        Stack<Integer> st=new Stack<>();
        for(char c:post.toCharArray()){
            if(Character.isDigit(c)) st.push(c-'0');
            else {
                int b=st.pop(), a=st.pop();
                st.push(c=='+'?a+b:c=='-'?a-b:c=='*'?a*b:a/b);
            }
        }
        return st.pop();
    }

    public static void main(String[] args){
        String expr="3+(2*5)-8/2";
        String post=toPostfix(expr);
        System.out.println("Postfix: "+post);
        System.out.println("Value: "+eval(post));
    }
}
