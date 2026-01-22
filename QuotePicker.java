import java.util.*;

public class QuotePicker {
    public static void main(String[] args) {
        List<String> q = List.of("Stay sharp", "Build fast", "Ship code");
        System.out.println(q.get(new Random().nextInt(q.size())));
    }
}
