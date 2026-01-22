import java.io.File;

public class FileOrganizers {
    public static void main(String[] args) {
        for (File f : new File(".").listFiles()) {
            if (f.isFile()) {
                String ext = f.getName().contains(".") ?
                        f.getName().substring(f.getName().lastIndexOf(".")) : "misc";
                new File(ext).mkdirs();
                f.renameTo(new File(ext + "/" + f.getName()));
            }
        }
    }
}
