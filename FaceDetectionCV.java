import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.global.opencv_imgcodecs.*;

public class FaceDetectionCV {

    public static void main(String[] args) {
        String imagePath = "face_input.jpg";
        String outputPath = "face_output.jpg";

        // Load image
        Mat image = opencv_imgcodecs.imread(imagePath);

        // Load Haar cascade file for face detection
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt.xml");

        // Detect faces
        RectVector faces = new RectVector();
        faceDetector.detectMultiScale(image, faces);

        // Draw rectangles around detected faces
        for (int i = 0; i < faces.size(); i++) {
            Rect rect = faces.get(i);
            opencv_imgproc.rectangle(
                    image,
                    new Point(rect.x(), rect.y()),
                    new Point(rect.x() + rect.width(), rect.y() + rect.height()),
                    new Scalar(0, 255, 0, 0),
                    3,
                    8,
                    0
            );
        }

        // Save the output
        opencv_imgcodecs.imwrite(outputPath, image);
        System.out.println("Faces detected: " + faces.size());
    }
}
