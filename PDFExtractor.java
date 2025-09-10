import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;

public class PDFExtractor {
    public static void main(String[] args) throws Exception {
        PDDocument doc = PDDocument.load(new File("sample.pdf"));
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        System.out.println("📄 Extracted Text:\n" + text);
        doc.close();
    }
}
