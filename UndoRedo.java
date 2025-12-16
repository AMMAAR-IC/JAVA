import java.util.*;

public class UndoRedo {
    static Stack<String> undo = new Stack<>();
    static Stack<String> redo = new Stack<>();

    static void type(String s) {
        undo.push(s);
        redo.clear();
    }

    static String undo() {
        if (!undo.isEmpty()) {
            redo.push(undo.pop());
        }
        return undo.isEmpty() ? "" : undo.peek();
    }

    static String redo() {
        if (!redo.isEmpty()) {
            undo.push(redo.pop());
        }
        return undo.peek();
    }

    public static void main(String[] args) {
        type("A");
        type("AB");
        type("ABC");
        System.out.println(undo());
        System.out.println(redo());
    }
}
