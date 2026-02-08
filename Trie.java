import java.util.*;

class TrieNode {
    Map<Character,TrieNode> c = new HashMap<>();
    boolean end = false;
}

public class Trie {
    static TrieNode root = new TrieNode();

    static void insert(String w){
        TrieNode t=root;
        for(char x:w.toCharArray())
            t=t.c.computeIfAbsent(x,k->new TrieNode());
        t.end=true;
    }

    static boolean search(String w){
        TrieNode t=root;
        for(char x:w.toCharArray()){
            t=t.c.get(x);
            if(t==null) return false;
        }
        return t.end;
    }

    public static void main(String[] args){
        insert("java");
        insert("javascript");
        System.out.println(search("java"));
        System.out.println(search("python"));
    }
}
