// ExprInterpreter.java
import java.util.*;
import java.util.regex.*;

public class ExprInterpreter {
    static Map<String, Double> vars = new HashMap<>();

    public static double eval(String expr) {
        Tokenizer t = new Tokenizer(expr);
        return parseExpression(t);
    }

    // grammar: expr = term { (+|-) term }
    static double parseExpression(Tokenizer t) {
        double val = parseTerm(t);
        while (t.hasNext() && (t.peek().equals("+") || t.peek().equals("-"))) {
            String op = t.next();
            double rhs = parseTerm(t);
            val = op.equals("+") ? val + rhs : val - rhs;
        }
        return val;
    }

    // term = factor { (*|/) factor }
    static double parseTerm(Tokenizer t) {
        double val = parseFactor(t);
        while (t.hasNext() && (t.peek().equals("*") || t.peek().equals("/"))) {
            String op = t.next();
            double rhs = parseFactor(t);
            val = op.equals("*") ? val * rhs : val / rhs;
        }
        return val;
    }

    // factor = primary [ ^ factor ]
    static double parseFactor(Tokenizer t) {
        double val = parsePrimary(t);
        if (t.hasNext() && t.peek().equals("^")) {
            t.next();
            double rhs = parseFactor(t);
            val = Math.pow(val, rhs);
        }
        return val;
    }

    // primary = number | identifier | (expr)
    static double parsePrimary(Tokenizer t) {
        String token = t.peek();
        if (token.equals("(")) {
            t.next();
            double v = parseExpression(t);
            t.next(); // )
            return v;
        } else if (isNumber(token)) {
            return Double.parseDouble(t.next());
        } else {
            // variable
            String id = t.next();
            if (!vars.containsKey(id)) throw new RuntimeException("Undefined var: " + id);
            return vars.get(id);
        }
    }

    static boolean isNumber(String s) {
        return s != null && s.matches("-?\\d+(\\.\\d+)?");
    }

    static class Tokenizer {
        LinkedList<String> tokens = new LinkedList<>();
        Tokenizer(String s) {
            Matcher m = Pattern.compile("\\s*([A-Za-z_]\\w*|\\d+\\.\\d+|\\d+|\\+|\\-|\\*|/|\\^|\\(|\\)|=)\\s*").matcher(s);
            while (m.find()) tokens.add(m.group(1));
        }
        boolean hasNext(){ return !tokens.isEmpty(); }
        String peek(){ return tokens.peek(); }
        String next(){ return tokens.poll(); }
    }

    public static void main(String[] args) {
        // Example REPL
        Scanner sc = new Scanner(System.in);
        System.out.println("Tiny Expr Interpreter. Type 'exit' to quit.");
        while (true) {
            System.out.print(">>> ");
            String line = sc.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;
            if (line.isEmpty()) continue;

            try {
                // assignment: var = expr
                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String var = parts[0].trim();
                    double val = eval(parts[1].trim());
                    vars.put(var, val);
                    System.out.println(var + " = " + val);
                } else if (line.startsWith("print(") && line.endsWith(")")) {
                    String inside = line.substring(6, line.length()-1);
                    System.out.println(eval(inside));
                } else {
                    System.out.println(eval(line));
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
