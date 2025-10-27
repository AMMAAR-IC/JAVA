// SimpleREPL.java
import javax.script.*;
import java.util.Scanner;

public class SimpleREPL {
    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js"); // or "nashorn" depending on JDK
        if (engine == null) engine = new ScriptEngineManager().getEngineByName("javascript");
        Scanner sc = new Scanner(System.in);
        System.out.println("Mini REPL (type 'exit' to quit)");
        while (true) {
            System.out.print("js> ");
            String line = sc.nextLine();
            if (line.equals("exit")) break;
            try { System.out.println(engine.eval(line)); } catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
        }
    }
}
