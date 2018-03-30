package model.bacteria.behavior;


/**
 * enumeration representing all types of decorators a behavior can have.
 */
public enum BehaviorDecoratorOption {
    /**
     * Represents a CostFilterDecisionBehavior.
     */
    COST_FILTER("Don't tries actions if has not enough energy to perform them"),
    /**
     * Represents a PreferentialDecisionBehavior relative to the ActionType MOVE.
     */
    PREFERENTIAL_MOVE("add priority to movement"),
    /**
     * Represents a PreferentialDecisionBehavior relative to the ActionType EAT.
     */
    PREFERENTIAL_EAT("add priority to eating"),
    /**
     * Represents a PreferentialDecisionBehavior relative to the ActionType
     * REPLICATE.
     */
    PREFERENTIAL_REPLICATE("add priority to replication");

    private final String description;

    BehaviorDecoratorOption(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
