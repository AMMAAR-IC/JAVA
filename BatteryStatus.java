import java.nio.file.*;

public class BatteryStatus {
    public static void main(String[] args) throws Exception {
        String cap = Files.readString(Path.of("/sys/class/power_supply/BAT0/capacity")).trim();
        String stat = Files.readString(Path.of("/sys/class/power_supply/BAT0/status")).trim();
        System.out.println("Battery: " + cap + "% (" + stat + ")");
    }
}
