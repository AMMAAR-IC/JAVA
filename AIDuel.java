import java.util.Random;

public class AIDuel {
    static Random rand = new Random();

    static class Fighter {
        String name;
        int hp = 100;

        Fighter(String name) { this.name = name; }

        int attack() { return rand.nextInt(20) + 5; }

        int defend() { return rand.nextInt(10) + 2; }

        boolean isAlive() { return hp > 0; }
    }

    public static void main(String[] args) {
        Fighter bot1 = new Fighter("⚙️ BotX");
        Fighter bot2 = new Fighter("🤖 BotZ");

        int turn = 1;
        while (bot1.isAlive() && bot2.isAlive()) {
            System.out.println("\n--- Turn " + turn + " ---");

            fightRound(bot1, bot2);
            if (!bot2.isAlive()) break;

            fightRound(bot2, bot1);
            turn++;
        }

        System.out.println("\nWinner: " + (bot1.isAlive() ? bot1.name : bot2.name));
    }

    static void fightRound(Fighter attacker, Fighter defender) {
        int atk = attacker.attack();
        int def = defender.defend();
        int damage = Math.max(0, atk - def);
        defender.hp -= damage;

        System.out.println(attacker.name + " attacks for " + atk + " → " + defender.name + " defends with " + def + " → HP: " + Math.max(0, defender.hp));
    }
}
