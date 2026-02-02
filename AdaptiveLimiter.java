public class AdaptiveLimiter {
    static int limit = 5, count = 0;
    static long window = System.currentTimeMillis();

    static boolean allow(){
        long now = System.currentTimeMillis();
        if(now - window > 1000){
            limit = count < limit ? limit + 1 : Math.max(1, limit - 1);
            count = 0;
            window = now;
        }
        count++;
        return count <= limit;
    }

    public static void main(String[] args){
        for(int i=0;i<15;i++)
            System.out.println(allow());
    }
}
