import java.nio.file.*;

public class ConfigHotReload {
    public static void main(String[] args) throws Exception {
        Path p = Path.of(".");
        WatchService ws = FileSystems.getDefault().newWatchService();
        p.register(ws, StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Watching config.txt...");
        while(true){
            for(var e : ws.take().pollEvents()){
                if(e.context().toString().equals("config.txt")){
                    System.out.println("Config updated!");
                    System.out.println(Files.readString(Path.of("config.txt")));
                }
            }
        }
    }
}
