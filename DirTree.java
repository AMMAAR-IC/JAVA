import java.io.File;

public class DirTree {
    public static void main(String[] args) {
        walk(new File("."), "");
    }

    static void walk(File f, String indent) {
        System.out.println(indent + "- " + f.getName());
        if (f.isDirectory())
            for (File c : f.listFiles())
                walk(c, indent + "  ");
    }
}
