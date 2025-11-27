// ProcessTree.java
import java.nio.file.*;
import java.util.*;

public class ProcessTree {
    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, String> names = new HashMap<>();

        for (var p : Files.newDirectoryStream(Path.of("/proc"))) {
            try {
                int pid = Integer.parseInt(p.getFileName().toString());
                String stat = Files.readString(p.resolve("stat"));
                String[] parts = stat.split(" ");
                int ppid = Integer.parseInt(parts[3]);
                String name = parts[1].replace("(", "").replace(")", "");
                parent.put(pid, ppid);
                names.put(pid, name);
            } catch (Exception ignored) {}
        }

        print(1, 0, parent, names);  // start from PID 1
    }

    static void print(int pid, int depth, Map<Integer,Integer> parent, Map<Integer,String> names){
        System.out.println(" ".repeat(depth * 2) + pid + " → " + names.get(pid));
        for (int c : parent.keySet())
            if (parent.get(c) == pid)
                print(c, depth + 1, parent, names);
    }
}
