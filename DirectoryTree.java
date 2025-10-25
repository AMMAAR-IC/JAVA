import java.io.File;

public class DirectoryTree {
    public static void main(String[] args) {
        File dir = new File(".");
        printTree(dir, "");
    }

    public static void printTree(File dir, String indent) {
        if (!dir.isDirectory()) return;
        for (File file : dir.listFiles()) {
            System.out.println(indent + "|-- " + file.getName());
            if (file.isDirectory()) printTree(file, indent + "    ");
        }
    }
}
