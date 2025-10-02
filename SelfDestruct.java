// SelfDestruct.java
import java.io.File;

public class SelfDestruct {
    public static void main(String[] args) throws Exception {
        String path = new java.io.File(SelfDestruct.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        System.out.println("Deleting: " + path);
        new File(path).delete();
    }
}
