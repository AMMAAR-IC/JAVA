import java.nio.file.*;
import java.util.*;

public class ObjParser {
    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Path.of("model.obj"));
        List<double[]> verts = new ArrayList<>();
        List<int[]> faces = new ArrayList<>();

        for (String l : lines) {
            if (l.startsWith("v ")) {
                String[] p = l.split(" ");
                verts.add(new double[]{Double.parseDouble(p[1]), Double.parseDouble(p[2]), Double.parseDouble(p[3])});
            }
            if (l.startsWith("f ")) {
                String[] p = l.split(" ");
                faces.add(new int[]{Integer.parseInt(p[1]), Integer.parseInt(p[2]), Integer.parseInt(p[3])});
            }
        }

        System.out.println("Vertices: " + verts.size());
        System.out.println("Faces: " + faces.size());
    }
}
