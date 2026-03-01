import java.util.*;

class Commit {
    String id;
    Commit parent;
    Commit(String id,Commit p){this.id=id;parent=p;}
}

public class GitGraph {
    public static void main(String[] args){
        Commit c1=new Commit("A",null);
        Commit c2=new Commit("B",c1);
        Commit c3=new Commit("C",c2);

        System.out.println(c3.parent.id);
    }
}
