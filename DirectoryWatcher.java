import java.nio.file.*;

public class DirectoryWatcher {
    public static void main(String[] args) throws Exception {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("C:/Users/Public/Documents"); // change path

        path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Watching folder: " + path);

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event: " + event.kind() + " | File: " + event.context());
            }
            key.reset();
        }
    }
}
