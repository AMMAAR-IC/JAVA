// JarDependencyLister.java
import java.io.*;
import java.util.jar.*;

public class JarDependencyLister {
    public static void main(String[] args) throws Exception {
        JarFile jar = new JarFile("app.jar");
        jar.stream().forEach(e -> {
            if (e.getName().endsWith(".class"))
                System.out.println(e.getName());
        });
    }
}
