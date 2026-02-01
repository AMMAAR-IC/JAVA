import java.util.*;

public class LogAggregator {
    static Deque<String> logs = new ArrayDeque<>();
    static int MAX = 5;

    static void log(String s){
        if(logs.size() == MAX) logs.pollFirst();
        logs.addLast(s);
        System.out.println(logs);
    }

    public static void main(String[] args){
        for(int i=1;i<=7;i++) log("event-"+i);
    }
}
