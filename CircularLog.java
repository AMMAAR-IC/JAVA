public class CircularLog {
    String[] buf;
    int idx = 0;

    CircularLog(int n){ buf = new String[n]; }

    void log(String s){
        buf[idx++ % buf.length] = s;
    }

    void dump(){
        for(String s:buf) if(s!=null) System.out.println(s);
    }

    public static void main(String[] args){
        CircularLog c = new CircularLog(3);
        c.log("A"); c.log("B"); c.log("C"); c.log("D");
        c.dump();
    }
}
