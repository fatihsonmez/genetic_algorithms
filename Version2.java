import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * The type Version 2.
 */
public class Version2 extends Evolution {

    /**
     * Instantiates a new Version 2.
     *
     * @param populationSize the population size
     */
    public Version2(int populationSize) {
        super(populationSize);
    }

    /**
     * This selection algorithm uses rank selection.
     * @return
     */
    @Override
    public Individual select() {
        /*
        Instead of their fitness value, we just rank them and select them according to their rank.
         */

        PriorityQueue<Individual> queue = new PriorityQueue<>(Collections.reverseOrder());

        queue.addAll(Arrays.asList(population.getIndividuals()));

        int total = (population.getPopulationSize() * (population.getPopulationSize() + 1)) / 2;
        Random random = new Random();
        int place = random.nextInt(total);
        int rouletteAt = 0;
        Individual individual;
        do{
            rouletteAt += queue.size();
            individual = queue.poll();
        }while(rouletteAt < place);

        return new Individual(individual);
    }

    /**
     * This function uses two point xover.
     * Randomly selects two points between 0-5 and 5-10 and then according to this range
     * does the xover.
     */
    @Override
    public void crossover() {
        Random random = new Random();
        int chromosomeLength = population.getIndividuals()[0].getLengthOfChromosome();
        int temp;
        int firstHalf = random.nextInt(chromosomeLength / 2);
        int secondHalf = random.nextInt(chromosomeLength - chromosomeLength / 2) + chromosomeLength / 2;


        for (int i = 0; i < chromosomeLength; i++) {
            if (i >= firstHalf && i <= secondHalf) {
                temp = this.fittest.getChromosomeX1()[i];
                this.fittest.getChromosomeX1()[i] = this.secondFittest.getChromosomeX1()[i];
                this.secondFittest.getChromosomeX1()[i] = temp;
            }
        }
        //crossover in chromosome1
        for (int i = 0; i < chromosomeLength; i++) {
            if (i >= firstHalf && i <= secondHalf) {
                temp = this.fittest.getChromosomeX2()[i];
                this.fittest.getChromosomeX2()[i] = this.secondFittest.getChromosomeX2()[i];
                this.secondFittest.getChromosomeX2()[i] = temp;
            }
        }

        fittest.setChromosomeValues();
        secondFittest.setChromosomeValues();

        System.out.println("change starts at: " + firstHalf + " ends at: " + secondHalf);
    }

}
