import java.util.*;

class TNode {
    Map<Character,TNode> c = new HashMap<>();
    boolean end;
}

public class TrieSuggest {
    static TNode root = new TNode();

    static void insert(String w){
        TNode t=root;
        for(char ch:w.toCharArray())
            t=t.c.computeIfAbsent(ch,k->new TNode());
        t.end=true;
    }

    static void suggest(TNode node,String prefix){
        if(node.end) System.out.println(prefix);
        for(var e:node.c.entrySet())
            suggest(e.getValue(),prefix+e.getKey());
    }

    static void searchPrefix(String p){
        TNode t=root;
        for(char ch:p.toCharArray()){
            t=t.c.get(ch);
            if(t==null) return;
        }
        suggest(t,p);
    }

    public static void main(String[] args){
        insert("java");
        insert("javascript");
        insert("jar");
        searchPrefix("ja");
    }
}
