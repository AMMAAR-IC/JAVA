import java.util.function.Predicate;

public class Router {
    static void route(String req){
        if(req.startsWith("/api")) System.out.println("API handler");
        else if(req.startsWith("/admin")) System.out.println("Admin handler");
        else System.out.println("Static handler");
    }

    public static void main(String[] args){
        route("/api/users");
        route("/admin");
    }
}
