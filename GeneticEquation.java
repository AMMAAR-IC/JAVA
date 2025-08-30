import java.util.*;

class Individual {
    int x, y;
    int fitness;

    public Individual() {
        Random r = new Random();
        x = r.nextInt(21) - 10; 
        y = r.nextInt(21) - 10;
        calcFitness();
    }

    public void calcFitness() {
        int value = (x * x) + (y * y);
        fitness = Math.abs(100 - value);
    }

    public Individual mate(Individual partner) {
        Individual child = new Individual();
        child.x = (this.x + partner.x) / 2;
        child.y = (this.y + partner.y) / 2;
        if (new Random().nextInt(10) < 2) child.x += new Random().nextInt(3) - 1;
        if (new Random().nextInt(10) < 2) child.y += new Random().nextInt(3) - 1;
        child.calcFitness();
        return child;
    }
}

public class GeneticEquation {
    public static void main(String[] args) {
        int populationSize = 100;
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) population.add(new Individual());

        int generation = 0;
        while (true) {
            Collections.sort(population, Comparator.comparingInt(a -> a.fitness));
            if (population.get(0).fitness == 0) {
                System.out.println("Solution found in generation " + generation + ": x=" +
                    population.get(0).x + ", y=" + population.get(0).y);
                break;
            }
            List<Individual> newPop = new ArrayList<>();
            for (int i = 0; i < populationSize / 2; i++) {
                Individual parent1 = population.get(i);
                Individual parent2 = population.get(new Random().nextInt(populationSize / 2));
                newPop.add(parent1.mate(parent2));
                newPop.add(parent2.mate(parent1));
            }
            population = newPop;
            generation++;
        }
    }
}
