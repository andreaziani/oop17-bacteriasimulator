package model.simulator;

import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

import model.Position;
import model.bacteria.Bacteria;

/**
 * Interface that represent all Bacteria in the simulation, provide method for its update.
 */
public interface BacteriaEnvironment {
    /**
     * Check whether the BacteriaEnvironment contains a Bacteria in given Position.
     * @param pos the position to be checked
     * @return true if the Position if occupied by a Bacteria, false otherwise
     */
    boolean containBacteriaInPosition(final Position pos);
    /**
     * Change the position of a Bacteria from oldPos to newPos.
     * @param oldPos the oldPosition in which the Bacteria is located
     * @param newPos the newPosition where the Bacteria should be moved
     */
    void changeBacteriaPosition(final Position oldPos, final Position newPos);
    /**
     * Get the bacteria located in given Position.
     * @param pos the position of the Bacteria to be retrieved
     * @return the Bacteria
     */
    Bacteria getBacteria(final Position pos);
    /**
     * Add new Bacteria to the BacteriaEnviroment.
     * @param position the Position in which insert the Bacteria
     * @param bacteria the Bacteria to insert
     */
    void insertBacteria(final Position position, final Bacteria bacteria);
    /**
     * Return the Set of Entry<Position, Bacteria> representing the BacteriaEnvironment.
     * @return the Set
     */
    Set<Entry<Position, Bacteria>> entrySet();
    /**
     * Remove a set of Position from the BacteriaEnvironment.
     * @param positions the set of Position to be removed
     */
    void removeFromPositions(final Set<Position> positions);
    /**
     * Return the state of the BacteriaEnviroment.
     * @return an unmodifiable map represent the state
     */
    Map<Position, Bacteria> getBacteriaState();
}