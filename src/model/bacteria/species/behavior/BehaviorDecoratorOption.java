package model.bacteria.species.behavior;

/**
 * enumeration representing all types of decorators a behavior can have.
 */
public enum BehaviorDecoratorOption {
    /**
     * Represent a ExplorerDecisionBehavior.
     */
    EXPLORE("Explore the simulation if no food better way to move is found"),
    /**
     * Represents a CostFilterDecisionBehavior.
     */
    COST_FILTER("Doesn't try actions if has not enough energy to perform them"),
    /**
     * Represents a PreferentialDecisionBehavior relative to the ActionType MOVE.
     */
    PREFERENTIAL_MOVE("Add priority to movement"),
    /**
     * Represents a PreferentialDecisionBehavior relative to the ActionType EAT.
     */
    PREFERENTIAL_EAT("Add priority to eating"),
    /**
     * Represents a PreferentialDecisionBehavior relative to the ActionType
     * REPLICATE.
     */
    PREFERENTIAL_REPLICATE("Add priority to replication");

    private final String description;

    BehaviorDecoratorOption(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
