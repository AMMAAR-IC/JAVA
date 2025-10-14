// MdToHtml.java
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import java.nio.file.*;

public class MdToHtml {
    public static void main(String[] args) throws Exception {
        Path md = Paths.get(args.length>0 ? args[0] : "README.md");
        String source = Files.readString(md);
        Parser parser = Parser.builder().build();
        Node document = parser.parse(source);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String html = renderer.render(document);
        Files.writeString(Paths.get("out.html"), html);
        System.out.println("Converted to out.html");
    }
}
