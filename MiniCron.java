import java.util.*;

public class MiniCron {
    static Timer timer=new Timer();

    public static void main(String[] args){
        timer.schedule(new TimerTask(){
            public void run(){
                System.out.println("Running job...");
            }
        },0,2000);
    }
}
