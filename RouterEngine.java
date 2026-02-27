import java.util.*;

public class RouterEngine {
    static Map<String,Runnable> routes=new HashMap<>();

    public static void main(String[] args){
        routes.put("/home",()->System.out.println("Home"));
        routes.put("/api",()->System.out.println("API"));

        handle("/api");
    }

    static void handle(String path){
        routes.getOrDefault(path,
                ()->System.out.println("404")).run();
    }
}
