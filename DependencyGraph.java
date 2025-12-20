import java.lang.reflect.*;

public class DependencyGraph {
    public static void main(String[] args) {
        for (Field f : Sample.class.getDeclaredFields())
            System.out.println(f.getType().getSimpleName());
    }

    static class Sample {
        String name;
        Integer age;
    }
}
