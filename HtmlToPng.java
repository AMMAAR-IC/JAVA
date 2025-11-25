// HtmlToPng.java
public class HtmlToPng {
    public static void main(String[] args) throws Exception {
        new ProcessBuilder("wkhtmltoimage","https://github.com","site.png")
            .inheritIO().start().waitFor();
        System.out.println("Saved site.png");
    }
}
