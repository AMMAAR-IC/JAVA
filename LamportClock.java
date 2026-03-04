public class LamportClock {

    static int time=0;

    static void event(){
        time++;
    }

    static void receive(int other){
        time=Math.max(time,other)+1;
    }

    public static void main(String[] args){
        event();
        receive(5);
        System.out.println(time);
    }
}
