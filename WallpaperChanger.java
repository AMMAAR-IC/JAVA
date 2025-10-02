// WallpaperChanger.java
import java.nio.file.*;
import java.util.*;

public class WallpaperChanger {
    public static void main(String[] args) throws Exception {
        List<String> imgs = List.of("1.jpg","2.jpg","3.jpg");
        Random rand = new Random();
        while (true) {
            String img = imgs.get(rand.nextInt(imgs.size()));
            if (System.getProperty("os.name").contains("Win"))
                Runtime.getRuntime().exec("reg add \"HKCU\\Control Panel\\Desktop\" /v Wallpaper /t REG_SZ /d " + Paths.get(img).toAbsolutePath() + " /f");
            else
                Runtime.getRuntime().exec(new String[]{"gsettings", "set", "org.gnome.desktop.background", "picture-uri", "file://" + Paths.get(img).toAbsolutePath()});
            Thread.sleep(5000);
        }
    }
}
