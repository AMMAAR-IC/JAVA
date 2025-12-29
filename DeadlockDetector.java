import java.lang.management.*;

public class DeadlockDetector {
    public static void main(String[] args)throws Exception{
        ThreadMXBean t=ManagementFactory.getThreadMXBean();
        while(true){
            long[] list=t.findDeadlockedThreads();
            if(list!=null) System.out.println("Deadlock Found!");
            Thread.sleep(1000);
        }
    }
}
