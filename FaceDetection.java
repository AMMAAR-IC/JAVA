import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.Imgproc;

public class FaceDetection {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        String imgPath = "face.jpg";
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_default.xml");
        Mat image = Imgcodecs.imread(imgPath);

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        System.out.println("Detected faces: " + faceDetections.toArray().length);

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0), 2);
        }

        Imgcodecs.imwrite("output.png", image);
        System.out.println("✅ Faces marked and saved in output.png");
    }
}
