import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Evolution.
 */
public abstract class Evolution implements Runnable {

    /**
     * This is for keeping the population
     */
    protected Population population;

    /**
     * This is the fittest individual.
     */
    protected Individual fittest;
    /**
     * This is the second fittest individual.
     */
    protected Individual secondFittest;
    /**
     * This is for showing stuff.
     */
    protected JFrame jFrame;
    /**
     * This is the number of generation but actually it shows the iteration and each iteration may not breed successful
     * child.
     */
    protected int generation;

    /**
     * Constructor for Evolution.
     *
     * @param populationSize the population size
     */
    Evolution(int populationSize) {
        population = new Population(populationSize);
    }

    /**
     * Setting the fittest.
     *
     * @param fittest the fittest
     */
    public void setFittest(Individual fittest) {
        this.fittest = fittest;
    }

    /**
     * Setting the second fittest.
     *
     * @param secondFittest the second fittest
     */
    public void setSecondFittest(Individual secondFittest) {
        this.secondFittest = secondFittest;
    }

    /**
     * This function selects two individual and pair them.
     *
     * @return the individual
     */
    public abstract Individual select();

    /**
     * This is the main xover since two of the versions uses this.
     * This is one point xover, randomly chooses a place and then do xover between
     * assigned fittest individual and second fittest individual.
     */
    public void crossover() {
        Random random = new Random();

        int crossoverPoint = random.nextInt(this.fittest.getLengthOfChromosome());
        int temp;

        for (int i = 0; i < crossoverPoint; i++) {
            temp = this.fittest.getChromosomeX1()[i];
            this.fittest.getChromosomeX1()[i] = this.secondFittest.getChromosomeX1()[i];
            this.secondFittest.getChromosomeX1()[i] = temp;
        }
        //crossover in chromosome1
        for (int i = 0; i < crossoverPoint; i++) {
            temp = this.fittest.getChromosomeX2()[i];
            this.fittest.getChromosomeX2()[i] = this.secondFittest.getChromosomeX2()[i];
            this.secondFittest.getChromosomeX2()[i] = temp;
        }

        fittest.setChromosomeValues();
        secondFittest.setChromosomeValues();
    }

    /**
     * After the breeding sequence returns the best child according to fitness value.
     *
     * @return fittest child
     */
    public Individual getFittestOffspring() {

        fittest.setChromosomeValues();
        secondFittest.setChromosomeValues();
        if (fittest.getFitness() > secondFittest.getFitness())
            return fittest;
        return secondFittest;
    }

    /**
     * Returns the least index of least
     *
     * @return least fittest individual
     */
    public int getIndexOfLeastFittest() {
        return this.population.getLeastFittestIndex();
    }

    /**
     * Evolution forged the entirety of the sentient life using only one tool: mistake.
     * Why wouldn't we?
     * Mutation isn't controlled in the flow that's why in here mutation rate is lower.
     * The mutation rate for each gene is %2 and there are 10 genes in each chromosome.
     * Mutates every gene(with 2% possibility) for two children.
     */
    public void mutate() {
        Random random = new Random();

        for (int i = 0; i < fittest.getChromosomeX1().length; i++) {
            if (random.nextInt(100) % 50 == 0) {
                if (fittest.getChromosomeX1()[i] == 0)
                    fittest.getChromosomeX1()[i] = 1;
                else
                    fittest.getChromosomeX1()[i] = 0;
            }
        }
        for (int i = 0; i < fittest.getChromosomeX2().length; i++) {
            if (random.nextInt(100) % 50 == 0) {
                if (fittest.getChromosomeX2()[i] == 0)
                    fittest.getChromosomeX2()[i] = 1;
                else
                    fittest.getChromosomeX2()[i] = 0;
            }
        }

        for (int i = 0; i < secondFittest.getChromosomeX1().length; i++) {
            if (random.nextInt(100) % 50 == 0) {
                if (secondFittest.getChromosomeX1()[i] == 0)
                    secondFittest.getChromosomeX1()[i] = 1;
                else
                    secondFittest.getChromosomeX1()[i] = 0;
            }
        }
        for (int i = 0; i < secondFittest.getChromosomeX2().length; i++) {
            if (random.nextInt(100) % 50 == 0) {
                if (secondFittest.getChromosomeX2()[i] == 0)
                    secondFittest.getChromosomeX2()[i] = 1;
                else
                    secondFittest.getChromosomeX2()[i] = 0;
            }
        }

        fittest.setChromosomeValues();

        secondFittest.setChromosomeValues();
    }

    /**
     * Takes a graph sets it as its own
     *
     * @param jFrame the j frame
     */
    public void setGraph(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    /**
     * Assigns the best one two the worst one's place.
     */
    public void addFittestOffspring() {
        int index = this.getIndexOfLeastFittest();
        if (getFittestOffspring().canSurvive())
            if (population.getIndividuals()[index].getFitness() <= getFittestOffspring().getFitness())
                population.getIndividuals()[index] = getFittestOffspring();
    }

    /**
     * This is for thread making.
     */
    @Override
    public void run() {

        int j = 0;
        do {

            Individual individual1 = this.select();
            Individual individual2 = this.select();

            //choosing fittest is okay
            this.setFittest(individual1);
            this.setSecondFittest(individual2);

            //now we need to crossover
            this.crossover();

            this.mutate();

            this.addFittestOffspring();
            this.generation = j;
            System.out.println("Generation " + j + " the fittest:");
            System.out.println(this.population.getFittest());
            System.out.println("Average fitness is: " + this.population.averageFitness());
            System.out.println("\n");

            this.jFrame.repaint();

            j++;
        }
        while (this.population.averageFitness() <= 126);

    }
}
