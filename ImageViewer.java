// ImageViewer.java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class ImageViewer extends Application {
    @Override
    public void start(Stage stage) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(stage);
        if (f == null) { System.out.println("No file"); System.exit(0); }
        Image img = new Image(f.toURI().toString());
        ImageView iv = new ImageView(img);
        iv.setPreserveRatio(true);
        iv.setFitWidth(800);
        stage.setScene(new Scene(new StackPane(iv), 800, 600));
        stage.setTitle("Image Viewer");
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}
