// FileWatcher.java
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

public class FileWatcher {
    public static void main(String[] args) throws Exception {
        WatchService ws = FileSystems.getDefault().newWatchService();
        Path dir = Path.of("C:/testwatch");
        dir.register(ws, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

        System.out.println("Watching: " + dir);
        while (true) {
            WatchKey key = ws.take();
            for (WatchEvent<?> ev : key.pollEvents())
                System.out.println(ev.kind() + ": " + ev.context());
            key.reset();
        }
    }
}
