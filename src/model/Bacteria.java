package model;

/**
 * The interface representing a bacteria in the simulation.
 */
public interface Bacteria {
    /**
     * @param perception
     *            a perception that will be set to be the current perception of the
     *            bacteria.
     */
    void setPerception(Perception perception);

    /**
     * @return an action the bacteria will choose given the current perception it
     *         has.
     */
    Action getAction();

    /**
     * @return the genetic code of the bacteria.
     */
    GeneticCode getGeneticCode();

    /**
     * @param code
     *            a new genetic code for the bacteria, generally called after a
     *            mutation of the previous code.
     */
    void setGeneticCode(GeneticCode code);

    /**
     * @return the amount of energy this bacteria currently have as a reserve.
     */
    Energy getEnergy();

    /**
     * @return if the bacteria is no more able to act.
     */
    boolean isDead();

    // TODO get/addNutrients
    /**
     * @param amount
     *            an amount of energy that the bacteria must spend from his reserve.
     * @throws NotEnounghEnergyException
     *             if the bacteria would have less that 0 energy after spending the
     *             given amount.
     */
    void spendEnergy(Energy amount);
}
