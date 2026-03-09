import java.util.*;

public class DiceRoll {

    public static void main(String[] args){

        Random r=new Random();

        System.out.println("Dice: "+(r.nextInt(6)+1));
    }
}
