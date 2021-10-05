import java.util.Random;

/**
 * The type Version 3.
 */
public class Version3 extends Evolution {

    /**
     * Instantiates a new Version 3.
     *
     * @param populationSize the population size
     */
    public Version3(int populationSize) {
        super(populationSize);
    }

    /**
     * This function uses tournament selection which after selecting people randomly, function makes them fight and
     * chooses the fittest.
     * @return
     */
    @Override
    public Individual select() {
        System.out.println("Tournament selection is sth");

        Individual best = null;
        Individual individual;
        Random random = new Random();
        int attendeeNumber = random.nextInt(population.getPopulationSize()/2) + 1; //at least one attendee
        for(int i = 0; i < attendeeNumber; i++){
            individual = population.getIndividuals()[random.nextInt(population.getPopulationSize())];
            if(best == null || individual.getFitness() > best.getFitness()){
                best = individual;
            }
        }
        return new Individual(best);
        /*
        * func tournament_selection(pop, k):
            best = null
            for i=1 to k
            ind = pop[random(1, N)]
            if (best == null) or fitness(ind) > fitness(best)
            best = ind
            return best
        *
        * */
    }

}
