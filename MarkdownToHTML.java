import java.nio.file.*;
import java.io.*;

public class MarkdownToHTML {
    public static void main(String[] args) throws Exception {
        String markdown = Files.readString(Path.of("input.md"));
        String html = markdown
            .replaceAll("(?m)^# (.*)$", "<h1>$1</h1>")
            .replaceAll("(?m)^## (.*)$", "<h2>$1</h2>")
            .replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")
            .replaceAll("\\*(.*?)\\*", "<i>$1</i>");

        Files.writeString(Path.of("output.html"), html);
        System.out.println("Converted input.md → output.html");
    }
}
