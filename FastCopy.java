import java.nio.file.*;
import java.nio.channels.*;

public class FastCopy {
    public static void main(String[] args) throws Exception {
        FileChannel src = FileChannel.open(Path.of("a.txt"), StandardOpenOption.READ);
        FileChannel dst = FileChannel.open(Path.of("b.txt"),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        src.transferTo(0, src.size(), dst);

        src.close();
        dst.close();
    }
}
