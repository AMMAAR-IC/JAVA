import java.util.*;

public class EventStore {
    static List<String> events = new ArrayList<>();

    static void append(String e){ events.add(e); }
    static List<String> replay(){ return List.copyOf(events); }

    public static void main(String[] args){
        append("USER_CREATED");
        append("USER_UPDATED");
        System.out.println(replay());
    }
}
