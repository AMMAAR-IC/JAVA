import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.nio.file.Paths;

public class QRGenerator {
    public static void main(String[] args) {
        try {
            String text = "https://github.com/AMMAAR-IC/JAVA";
            BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 300, 300);
            MatrixToImageWriter.writeToPath(matrix, "PNG", Paths.get("qr.png"));
            System.out.println("QR Code generated as qr.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
