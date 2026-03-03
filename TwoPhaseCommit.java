import java.util.*;

public class TwoPhaseCommit {

    static boolean prepare(){
        return new Random().nextBoolean();
    }

    public static void main(String[] args){
        if(prepare())
            System.out.println("COMMIT");
        else
            System.out.println("ABORT");
    }
}
