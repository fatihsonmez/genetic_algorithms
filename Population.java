/**
 * The type Population.
 */
public class Population {

    private int populationSize;
    private Individual[] individuals;
    private double fittest;

    /**
     * Instantiates a new Population.
     *
     * @param populationSize the population size
     */
    Population(int populationSize){
        this.populationSize = populationSize;
        individuals = new Individual[populationSize];
        initializePopulation();
    }

    /**
     * Initialize the population.
     */
    private void initializePopulation(){
        for(int i = 0; i < populationSize; i++)
            individuals[i] = new Individual();
    }

    /**
     * Get individuals individual [ ].
     *
     * @return individuals. individual [ ]
     */
    public Individual[] getIndividuals() {
        return individuals;
    }

    /**
     * Gets population size.
     *
     * @return population size
     */
    public int getPopulationSize() {
        return populationSize;
    }

    /**
     * This is for showing the fittest, never used for selecting because we already have selection algorithms for that.
     *
     * @return individual individual
     */
    public Individual getFittest(){
        double maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for (int i = 0; i < populationSize; i++) {
            if (maxFit <= individuals[i].getFitness()) {
                maxFit = individuals[i].getFitness();
                maxFitIndex = i;
            }
        }
        fittest = individuals[maxFitIndex].getFitness();
        return individuals[maxFitIndex];
    }

    /**
     * Calculates the average fitness.
     *
     * @return double double
     */
    public double averageFitness(){
        double sum = 0;
        for (Individual individual: individuals)
            sum += individual.getFitness();

        return sum / getPopulationSize();
    }

    /**
     * Gets least fittest index.
     *
     * @return index of least fittest individual.
     */
    public int getLeastFittestIndex() {
        double minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < populationSize; i++) {
            if (minFitVal >= individuals[i].getFitness()) {
                minFitVal = individuals[i].getFitness();
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    /**
     * Overriding the tostring for printing properly.
     * @return
     */
    @Override
    public String toString() {
        String result = "";
        for (Individual i: individuals) {
            result += i.toString();
        }

        return result;
    }
}
