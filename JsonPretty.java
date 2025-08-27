import java.util.*;

public class JsonPretty {
    public static void main(String[] args) {
        String ugly = "{\"name\":\"Ammaar\",\"skills\":[\"Java\",\"ML\"]}";
        int indent = 0;
        for (char c : ugly.toCharArray()) {
            if ("{[".indexOf(c)>=0) { System.out.print(c+"\n"+"  ".repeat(++indent)); }
            else if ("}]".indexOf(c)>=0) { System.out.print("\n"+"  ".repeat(--indent)+c); }
            else if (c==',') { System.out.print(c+"\n"+"  ".repeat(indent)); }
            else System.out.print(c);
        }
    }
}
