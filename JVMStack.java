import java.util.*;

public class JVMStack {
    static Stack<String> frames=new Stack<>();

    static void call(String f){
        frames.push(f);
    }

    static void ret(){
        frames.pop();
    }

    public static void main(String[] args){
        call("main");
        call("funcA");
        System.out.println(frames);
        ret();
    }
}
