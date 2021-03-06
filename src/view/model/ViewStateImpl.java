package view.model;

import java.util.Collections;
import java.util.Map;

import view.model.bacteria.ViewBacteria;
import view.model.food.ViewProvision;

/**
 * Implementation of ViewState.
 *
 */
public final class ViewStateImpl implements ViewState {
    private final Map<ViewPosition, ViewProvision> foodsState;
    private final Map<ViewPosition, ViewBacteria> bacteriaState;

    /**
     * Constructor that create a ViewState from foodstate and bacteriastate.
     * 
     * @param foodsState
     *            state of foods present in simulation.
     * @param bacteriaState
     *            state of bacteria present in simulation.
     */
    public ViewStateImpl(final Map<ViewPosition, ViewProvision> foodsState,
            final Map<ViewPosition, ViewBacteria> bacteriaState) {
        this.foodsState = foodsState;
        this.bacteriaState = bacteriaState;
    }

    @Override
    public Map<ViewPosition, ViewProvision> getFoodsState() {
        return Collections.unmodifiableMap(this.foodsState);
    }

    @Override
    public Map<ViewPosition, ViewBacteria> getBacteriaState() {
        return Collections.unmodifiableMap(this.bacteriaState);
    }
}
