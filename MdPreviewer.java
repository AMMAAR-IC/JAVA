// MdPreviewer.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URLEncoder;

public class MdPreviewer extends Application {
    @Override public void start(Stage s) {
        TextArea ta = new TextArea("# Hello\nType markdown here");
        WebView view = new WebView();
        ta.textProperty().addListener((obs,old,nw) -> view.getEngine().loadContent(render(nw)));
        SplitPane sp = new SplitPane(ta, view);
        s.setScene(new Scene(sp, 900, 600)); s.show();
    }
    String render(String md) {
        // very simple markdown -> html replacements (replace with a proper lib later)
        String html = md.replaceAll("(?m)^# (.+)$", "<h1>$1</h1>").replaceAll("\n","<br/>");
        return "<html><body>" + html + "</body></html>";
    }
    public static void main(String[] a){ launch(); }
}
