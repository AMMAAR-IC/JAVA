import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;

public class PDFReader {
    public static void main(String[] args) {
        try {
            File file = new File("sample.pdf");
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            System.out.println("PDF Content:\n" + text);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
