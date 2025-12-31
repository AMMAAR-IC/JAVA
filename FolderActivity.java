import java.nio.file.*;

public class FolderActivity {
    public static void main(String[] args)throws Exception{
        WatchService ws=FileSystems.getDefault().newWatchService();
        Path p=Path.of(".");
        p.register(ws,StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while(true){
            for(var e:ws.take().pollEvents())
                System.out.println(e.kind()+" -> "+e.context());
        }
    }
}
