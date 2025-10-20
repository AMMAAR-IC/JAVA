// WebcamCapture.java
// Requires: org.bytedeco:javacv and opencv on classpath
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_videoio;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

public class WebcamCapture {
    public static void main(String[] args) {
        VideoCapture cap = new VideoCapture(0);
        if (!cap.isOpened()) { System.out.println("Camera not found"); return; }
        Mat frame = new Mat();
        cap.read(frame);
        opencv_imgcodecs.imwrite("capture.jpg", frame);
        cap.release();
        System.out.println("Saved capture.jpg");
    }
}
