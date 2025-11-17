import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import javax.imageio.ImageIO;
import java.io.File;

public class QRReader {
    public static void main(String[] args) throws Exception {
        var img = ImageIO.read(new File("qr.png"));
        var source = new BufferedImageLuminanceSource(img);
        var bitmap = new BinaryBitmap(new HybridBinarizer(source));
        System.out.println("Decoded: " +
            new MultiFormatReader().decode(bitmap).getText());
    }
}
