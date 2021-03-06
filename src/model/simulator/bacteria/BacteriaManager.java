package model.simulator.bacteria;

import java.util.Map;
import java.util.Optional;

import model.bacteria.Bacteria;
import model.state.Position;

/**
 * Interface used to update Bacteria every turn.
 *
 */
public interface BacteriaManager {
    /**
     * Update all Bacteria.
     */
    void updateBacteria();

    /**
     * Return bacteria state.
     * 
     * @return the state of bacteria in the environment
     */
    Map<Position, Bacteria> getBacteriaState();

    /**
     * Create the initial amount of Bacteria in the simulation.
     * 
     * @param bacteriaState
     *            an Optional Map of Position and Bacteria, if present use the Map
     *            to populate the simulation. Otherwise populate according to
     *            default parameters
     */
    void populate(Optional<Map<Position, Bacteria>> bacteriaState);
}
