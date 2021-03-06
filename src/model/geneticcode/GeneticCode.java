package model.geneticcode;

import model.Energy;
import model.action.Action;
import model.food.Nutrient;

/**
 * Interface of a GeneticCode type. It represent individual specific
 * characteristic of a Bacteria and can mutate while the bacteria is still
 * alive.
 */
public interface GeneticCode {
    /**
     * Get the Energy cost for executing an Action, depends on the action but also
     * on the particular GeneticCode.
     * 
     * @param action
     *          the action of bacteria that get cost.
     * 
     * @return the cost of executing that action with this GeneticCode.
     */
    Energy getActionCost(Action action);

    /**
     * @return the speed of a bacteria with this GeneticCode.
     */
    double getSpeed();

    /**
     * 
     * @return the code of bacteria.
     */
    Gene getCode();

    /**
     * Get the amount of Energy a Nutrient can provide to a Bacteria with this
     * GenetiCode.
     * @param nutrient
     *          type of nutrient.
     * @return the amount of Energy that the Bacteria can gain by eating the given
     *         Nutrient, it is negative if the Nutrient is nocive.
     */
    Energy getEnergyFromNutrient(Nutrient nutrient);

    /**
     * @return the radius of a bacteria with this GeneticCode.
     */
    Double getRadius();

    /**
     * @return the radius of the perception of a bacteria with this GeneticCode.
     */
    Double getPerceptionRadius();

}
