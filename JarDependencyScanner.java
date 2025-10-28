// JarDependencyScanner.java
import java.util.jar.*;
import java.nio.file.*;
import java.util.*;

public class JarDependencyScanner {
    public static void main(String[] args) throws Exception {
        Map<String, List<String>> graph = new HashMap<>();
        Files.list(Paths.get("libs")).filter(p->p.toString().endsWith(".jar")).forEach(p-> {
            try (JarFile jf = new JarFile(p.toFile())) {
                Manifest m = jf.getManifest();
                String cp = m == null ? null : m.getMainAttributes().getValue("Class-Path");
                if (cp != null) graph.put(p.getFileName().toString(), Arrays.asList(cp.split(" ")));
                else graph.put(p.getFileName().toString(), List.of());
            } catch (Exception e) { /* ignore */ }
        });
        graph.forEach((k,v)-> System.out.println(k + " -> " + v));
    }
}
