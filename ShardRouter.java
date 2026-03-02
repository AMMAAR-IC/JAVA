public class ShardRouter {

    static int shard(String key){
        return Math.abs(key.hashCode()%3);
    }

    public static void main(String[] args){
        System.out.println("User stored in shard "+shard("user42"));
    }
}
