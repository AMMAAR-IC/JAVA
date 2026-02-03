public class LeakyBucket {
    static int capacity = 5, water = 0;

    static boolean allow(){
        if(water < capacity){
            water++;
            return true;
        }
        return false;
    }

    static void leak(){ if(water > 0) water--; }

    public static void main(String[] args){
        for(int i=0;i<7;i++) System.out.println(allow());
        leak(); leak();
        System.out.println(allow());
    }
}
