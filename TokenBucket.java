public class TokenBucket {
    int tokens=5;
    long last=System.currentTimeMillis();

    boolean allow(){
        long now=System.currentTimeMillis();
        tokens += (now-last)/1000;
        if(tokens>5) tokens=5;
        last=now;

        if(tokens>0){
            tokens--;
            return true;
        }
        return false;
    }
}
