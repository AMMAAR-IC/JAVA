import java.io.*;

public class PipeExec {
    public static void main(String[] args) throws Exception {
        Process p1 = Runtime.getRuntime().exec("ls");
        Process p2 = Runtime.getRuntime().exec("wc -l");

        p1.getInputStream().transferTo(p2.getOutputStream());
        p2.getOutputStream().close();
        p2.getInputStream().transferTo(System.out);
    }
}
