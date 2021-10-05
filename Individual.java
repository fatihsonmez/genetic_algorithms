import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * The type Individual.
 */
public class Individual implements Comparable<Individual>{

    private double fitness;
    private int lengthOfChromosome = 10;
    private double valueOfChromosomeX1, valueOfChromosomeX2;
    private int[] chromosomeX1;
    private int[] chromosomeX2;

    /**
     * Instantiates a new Individual.
     *
     * @param individual the individual
     */
    public Individual(Individual individual){
        chromosomeX1 = individual.chromosomeX1;
        chromosomeX2 = individual.chromosomeX2;
        valueOfChromosomeX2 = individual.valueOfChromosomeX2;
        valueOfChromosomeX1 = individual.valueOfChromosomeX1;
        lengthOfChromosome = individual.lengthOfChromosome;
        fitness = individual.fitness;
    }

    /**
     * This is the constructor of the Individual class. This constructor is responsible for constraints.
     * Constraints are these:
     * chromosome1 + chromosome2 <= 5;
     * 0 <= chromosome1 <= 5 and 0 <= chromosome2 <= 5
     */
    public Individual() {
        setValueOfChromosomes();
        setChromosomes();
        computeFitness();
    }

    /**
     * Gets value of chromosome x 1.
     *
     * @return the value of chromosome x 1
     */
    public double getValueOfChromosomeX1() {
        return valueOfChromosomeX1;
    }

    /**
     * Gets value of chromosome x 2.
     *
     * @return the value of chromosome x 2
     */
    public double getValueOfChromosomeX2() {
        return valueOfChromosomeX2;
    }

    /**
     * This function sets the value of the chromosomes according to constraints.
     *
     */
    private void setValueOfChromosomes(){

        Random random = new Random();
        //Magic numbers are not actually magic numbers, 5 is constrain number and
        //1023 is 2^10-1 where 10 is length of the chromosome.

        //Getting values of the chromosomes
        valueOfChromosomeX1 = random.nextDouble() * 5;
        valueOfChromosomeX2 = random.nextDouble() * (5 - valueOfChromosomeX1);

        //But this values may not be okay with our representation system, so we change them
        int px = (int)((valueOfChromosomeX1 / 5 ) * 1023);
        int py = (int)((valueOfChromosomeX2 / 5 ) * 1023);

        //This is okay with 10 bit presentation
        valueOfChromosomeX1 = (px / 1023.0) * 5;
        valueOfChromosomeX2 = (py / 1023.0) * 5;
    }

    /**
     * This function sets the chromosome for 10 bit representation.
     */
    private void setChromosomes(){

        int px = (int)((valueOfChromosomeX1 / 5 ) * 1023);
        int py = (int)((valueOfChromosomeX2 / 5 ) * 1023);

        String chromosome1AsStringArray = String.format("%" + lengthOfChromosome + "s",
                Integer.toBinaryString(px)).replaceAll(" ", "0");
        String chromosome2AsStringArray = String.format("%" + lengthOfChromosome + "s",
                Integer.toBinaryString(py)).replaceAll(" ", "0");

        chromosomeX1 = new int[lengthOfChromosome];
        chromosomeX2 = new int[lengthOfChromosome];
        for(int i = 0; i < lengthOfChromosome; i++){
            chromosomeX1[i] = Character.digit(chromosome1AsStringArray.charAt(i), 10);
            chromosomeX2[i] = Character.digit(chromosome2AsStringArray.charAt(i), 10);
        }
    }

    /**
     * Returns the length of the chromosomes.
     *
     * @return lengthOfChromosome length of chromosome
     */
    public int getLengthOfChromosome() {
        return lengthOfChromosome;
    }

    /**
     * Returns the chromosomeX1
     *
     * @return chromosomeX1 int [ ]
     */
    public int[] getChromosomeX1() {
        return chromosomeX1;
    }

    /**
     * Returns the chromosomeX2
     *
     * @return chromosomeX2 int [ ]
     */
    public int[] getChromosomeX2() {
        return chromosomeX2;
    }

    /**
     * This function re-assigns the chromosome values after mutation and xover according to their genotype.
     */
    public void setChromosomeValues(){
        int sum = 0;
        for (int i = 0; i < lengthOfChromosome; i++){
            sum += chromosomeX1[i] * Math.pow(2, lengthOfChromosome - 1 - i);
        }
        valueOfChromosomeX1 = (sum / 1023.0) * 5;

        sum = 0;
        for (int i = 0; i < lengthOfChromosome; i++){
            sum += chromosomeX2[i] * Math.pow(2, lengthOfChromosome - 1 - i);
        }
        valueOfChromosomeX2 = (sum / 1023.0) * 5;

        computeFitness();
    }

    /**
     * This function calculates the fitness according to given function.
     */
    private void computeFitness() {
        this.fitness = (20 * valueOfChromosomeX1 * valueOfChromosomeX2) + (16 * valueOfChromosomeX2) -
                (2 * valueOfChromosomeX1 * valueOfChromosomeX1) - (valueOfChromosomeX2 * valueOfChromosomeX2) -
                (Math.pow((valueOfChromosomeX1 + valueOfChromosomeX2), 2));

    }

    /**
     * This function returns the fitness.
     *
     * @return fitness fitness
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * This function formats the Individual for string display.
     * @return string version of individual
     */
    @Override
    public String toString() {
        NumberFormat numberFormat = new DecimalFormat("#0.000");
        return "Chromosome1 value is " + numberFormat.format(valueOfChromosomeX1) + " and chromosome is: " + Arrays.toString(this.chromosomeX1) +
                "\nChromosome2 value is " + numberFormat.format(valueOfChromosomeX2) + " and chromosome is: " + Arrays.toString(this.chromosomeX2) +
                "\nFitness is: " + numberFormat.format(fitness) + "\n";
    }

    /**
     * This function is overridden for using priority queue.
     * @param individual
     * @return
     */
    @Override
    public int compareTo(Individual individual) {
        if(this.fitness > individual.getFitness())
            return 1;
        else if(this.fitness < individual.getFitness())
            return -1;
        return 0;
    }

    /**
     * After xover and mutation, this function checks if the individual violating constraints.
     * If so returns false.
     *
     * @return false if can't survive true if can survive
     */
    public boolean canSurvive(){
        return valueOfChromosomeX1 <= 5 && valueOfChromosomeX2 <= 5 && (valueOfChromosomeX1 + valueOfChromosomeX2) <= 5;
    }
}
