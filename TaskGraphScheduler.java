import java.util.*;

public class TaskGraphScheduler {
    static Map<String,List<String>> dep = new HashMap<>();
    static Set<String> done = new HashSet<>();

    static void run(String task){
        for(String d : dep.getOrDefault(task,List.of()))
            if(!done.contains(d)) run(d);

        if(done.add(task))
            System.out.println("Executed: " + task);
    }

    public static void main(String[] args){
        dep.put("build", List.of("compile","test"));
        dep.put("test", List.of("compile"));
        dep.put("compile", List.of("fetch"));

        run("build");
    }
}
