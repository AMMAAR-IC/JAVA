import java.util.*;

public class StackMachine {

    static Stack<Integer> stack = new Stack<>();

    static void execute(int[] code){
        for(int i=0;i<code.length;i++){
            switch(code[i]){
                case 1 -> stack.push(code[++i]);        // PUSH
                case 2 -> stack.push(stack.pop() + stack.pop()); // ADD
                case 3 -> System.out.println(stack.pop()); // PRINT
            }
        }
    }

    public static void main(String[] args){
        int[] program = {1,5,1,7,2,3};
        execute(program);
    }
}
