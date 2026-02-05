import java.util.*;

public class FeatureToggle {
    static Set<String> on = new HashSet<>();

    static void enable(String f){ on.add(f); }
    static boolean enabled(String f){ return on.contains(f); }

    public static void main(String[] args){
        enable("DARK_MODE");
        System.out.println(enabled("DARK_MODE"));
    }
}
