import javax.swing.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * The type Version 1.
 */
public class Version1 extends Evolution {

    /**
     * Constructor for version one.
     *
     * @param populationSize the population size
     */
    public Version1(int populationSize) {
        super(populationSize);
    }

    /**
     * This function selects an individual for mating pool. First calculates the total fitness. While calculating the total fitness
     * it adds Individuals to the priority queue. After that chooses a random value between 0 and total fitness, then pulls the
     * individuals from the list, sum up their fitness values and when it pass the chosen random value, stops and returns the individual.
     * <p>
     * If total fitness is less than zero,
     */
    @Override
    public Individual select() {

        PriorityQueue<Individual> queue = new PriorityQueue<>(Collections.reverseOrder());

        //This is going to help us to choose better parents
        double totalFitness = 0;
        for (Individual i : population.getIndividuals()) {
            if (i.getFitness() > 0)
                totalFitness += i.getFitness();
            queue.add(i);
        }

        Random random = new Random();
        double partialSum = 0;
        if (totalFitness >= 0)
            partialSum = random.nextDouble() * totalFitness;

        double cross = 0;
        Individual individual = null;
        while (cross <= partialSum) {
            individual = queue.poll();
            cross += individual.getFitness();
            /*
            System.out.println("Roulette at: " + cross);
            System.out.println("Processed individual: " + individual);*/
        }

        return new Individual(individual);
    }

}
