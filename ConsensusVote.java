import java.util.*;

public class ConsensusVote {
    public static void main(String[] args){
        List<Boolean> votes=List.of(true,true,false,true);

        long yes=votes.stream().filter(v->v).count();
        System.out.println(yes>votes.size()/2?"COMMIT":"ABORT");
    }
}
