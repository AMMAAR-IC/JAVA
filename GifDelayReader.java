import javax.imageio.*;
import javax.imageio.metadata.*;
import java.io.*;

public class GifDelayReader {
    public static void main(String[] args) throws Exception {
        var reader = ImageIO.getImageReadersByFormatName("gif").next();
        reader.setInput(ImageIO.createImageInputStream(new File("anim.gif")), false);

        int frames = reader.getNumImages(true);
        for (int i=0;i<frames;i++) {
            IIOMetadata meta = reader.getImageMetadata(i);
            String s = meta.getAsTree(meta.getNativeMetadataFormatName())
                           .toString();
            int pos = s.indexOf("delayTime=");
            System.out.println("Frame " + i + " delay = " + s.substring(pos+11, pos+13) + " cs");
        }
    }
}
