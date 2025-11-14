import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDMetadata;

import java.io.*;

public class PDFMeta {
    public static void main(String[] args) throws Exception {
        PDDocument pdf = PDDocument.load(new File("sample.pdf"));
        var info = pdf.getDocumentInformation();
        System.out.println("Title: " + info.getTitle());
        System.out.println("Author: " + info.getAuthor());
        System.out.println("Creator: " + info.getCreator());
        System.out.println("Producer: " + info.getProducer());
        pdf.close();
    }
}
