import net.sourceforge.tess4j.*;
import java.io.File;

public class ImageOCR {
    public static void main(String[] args) throws Exception {
        File imageFile = new File("text.png");
        Tesseract tess = new Tesseract();
        tess.setDatapath("tessdata"); // path to tessdata folder
        tess.setLanguage("eng");

        String result = tess.doOCR(imageFile);
        System.out.println("📄 Extracted Text:\n" + result);
    }
}
