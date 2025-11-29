// ObjectSizer.java
import sun.misc.Unsafe;
import java.lang.reflect.*;

public class ObjectSizer {
    static Unsafe u;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            u = (Unsafe) f.get(null);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public static void main(String[] args) {
        String s = "AMMAAR";
        System.out.println("Approx object size: " + u.getAddress(u.allocateMemory(8)));
    }
}
