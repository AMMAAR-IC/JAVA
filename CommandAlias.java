import java.util.*;

public class CommandAlias {
    static Map<String,String> map = new HashMap<>();

    public static void main(String[] args){
        map.put("ll","ls -la");
        execute("ll");
    }

    static void execute(String cmd){
        System.out.println(map.getOrDefault(cmd, cmd));
    }
}
