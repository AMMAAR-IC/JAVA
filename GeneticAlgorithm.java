import java.util.*;

class Individual {
    String genes;
    int fitness;

    Individual(int length) {
        char[] g = new char[length];
        for (int i = 0; i < length; i++) {
            g[i] = (char)(32 + new Random().nextInt(95)); // random ASCII chars
        }
        this.genes = new String(g);
        this.fitness = 0;
    }

    void calcFitness(String target) {
        int score = 0;
        for (int i = 0; i < target.length(); i++) {
            if (genes.charAt(i) == target.charAt(i)) score++;
        }
        this.fitness = score;
    }

    Individual crossover(Individual partner) {
        char[] childGenes = new char[genes.length()];
        int midpoint = new Random().nextInt(genes.length());
        for (int i = 0; i < genes.length(); i++) {
            childGenes[i] = (i > midpoint) ? genes.charAt(i) : partner.genes.charAt(i);
        }
        return new Individual(new String(childGenes));
    }

    Individual(String g) {
        this.genes = g;
        this.fitness = 0;
    }

    void mutate(double rate) {
        char[] g = genes.toCharArray();
        Random rand = new Random();
        for (int i = 0; i < g.length; i++) {
            if (rand.nextDouble() < rate) {
                g[i] = (char)(32 + rand.nextInt(95));
            }
        }
        genes = new String(g);
    }
}

public class GeneticAlgorithm {
    static final String TARGET = "HELLO GENETIC WORLD";
    static final int POP_SIZE = 200;
    static final double MUT_RATE = 0.01;

    public static void main(String[] args) {
        Random rand = new Random();
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < POP_SIZE; i++) {
            population.add(new Individual(TARGET.length()));
        }

        int generation = 0;
        while (true) {
            for (Individual ind : population) {
                ind.calcFitness(TARGET);
                if (ind.fitness == TARGET.length()) {
                    System.out.println("Generation " + generation + " | " + ind.genes);
                    return;
                }
            }

            population.sort((a, b) -> b.fitness - a.fitness);

            List<Individual> newPop = new ArrayList<>();
            for (int i = 0; i < POP_SIZE; i++) {
                Individual parentA = select(population);
                Individual parentB = select(population);
                Individual child = parentA.crossover(parentB);
                child.mutate(MUT_RATE);
                newPop.add(child);
            }
            population = newPop;

            System.out.println("Gen " + generation + " Best: " + population.get(0).genes + " Fitness: " + population.get(0).fitness);
            generation++;
        }
    }

    static Individual select(List<Individual> population) {
        Random rand = new Random();
        int index = rand.nextInt(50); // bias toward best 50
        return population.get(index);
    }
}
