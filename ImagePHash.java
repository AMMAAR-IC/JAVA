import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImagePHash {
    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("img.jpg"));
        long hash = hash(img);
        System.out.println(Long.toHexString(hash));
    }

    static long hash(BufferedImage img) {
        int size = 8;
        long h = 0;
        int avg = 0;
        int[] gray = new int[size * size];

        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++) {
                int rgb = img.getRGB(x * img.getWidth() / size, y * img.getHeight() / size);
                int g = ((rgb >> 16) & 255 + (rgb >> 8) & 255 + (rgb & 255)) / 3;
                gray[y * size + x] = g;
                avg += g;
            }

        avg /= gray.length;

        for (int i = 0; i < gray.length; i++)
            if (gray[i] > avg) h |= (1L << i);

        return h;
    }
}
