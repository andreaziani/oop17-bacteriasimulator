package model.simulator;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.AbstractEnvironment;
import model.AnalysisImpl;
import model.Energy;
import model.EnergyImpl;
import model.InteractiveEnvironment;
import model.MutationManager;
import model.MutationManagerImpl;
import model.PositionAlreadyOccupiedException;
import model.bacteria.Bacteria;
import model.bacteria.species.SpeciesManager;
import model.bacteria.species.SpeciesManagerImpl;
import model.bacteria.species.SpeciesOptions;
import model.food.ExistingFoodManager;
import model.food.ExistingFoodManagerImpl;
import model.food.Food;
import model.food.insertionstrategy.position.DistributionStrategy;
import model.simulator.bacteria.BacteriaManager;
import model.simulator.bacteria.BacteriaManagerImpl;
import model.simulator.food.FoodEnvironment;
import model.simulator.food.FoodEnvironmentImpl;
import model.state.InitialState;
import model.state.Position;
import model.state.State;
import model.state.StateImpl;
import utils.Logger;

/**
 * implementation of Environment.
 *
 */

public final class SimulatorEnvironment extends AbstractEnvironment implements InteractiveEnvironment {
    private static final int MAX_ITERATIONS = 240;
    private static final int FOOD_PER_ROUND = 2;
    private static final double DEFAULT_HEIGHT = 1000.0;
    private static final double DEFAULT_WIDTH = 1000.0;

    private final MutationManager mutManager = new MutationManagerImpl();
    private final ExistingFoodManager foodManager = new ExistingFoodManagerImpl();
    private final SpeciesManager speciesManager = new SpeciesManagerImpl();
    private final FoodEnvironment foodEnv = new FoodEnvironmentImpl(this.foodManager, this.getMaxPosition());
    private final BacteriaManager bactManager = new BacteriaManagerImpl(this.foodEnv, this.foodManager,
            this.getMaxPosition(), this.speciesManager);
    private Optional<State> state;
    private int iterations;

    /**
     * The amount of energy a Bacteria should have when created.
     */
    public static final Energy INITIAL_ENERGY = new EnergyImpl(700.0);

    private void updateFood() {
        IntStream.range(0, FOOD_PER_ROUND).forEach(x -> this.foodEnv.addRandomFood());
    }

    private void updateBacteria() {
        this.bactManager.updateBacteria();
        final List<Bacteria> aliveBacteria = this.bactManager.getBacteriaState().values().stream()
                .filter(bacteria -> !bacteria.isDead()).collect(Collectors.toList());
        this.mutManager.updateMutation(aliveBacteria);
    }

    /**
     * Create an empty environment.
     */
    public SimulatorEnvironment() {
        super(new InitialState(DEFAULT_WIDTH, DEFAULT_HEIGHT), new AnalysisImpl());
        state = Optional.empty();
    }

    /**
     * Create an environment from an initial state. If the initial state has also
     * already a state it initialize the environment with that state.
     * 
     * @param initialState
     *            an InitialState.
     */
    public SimulatorEnvironment(final InitialState initialState) {
        super(initialState, new AnalysisImpl());
        initialState.getExistingFood().forEach(this.foodManager::addFood);
        initialState.getSpecies().forEach(this.speciesManager::addSpecies);
        if (initialState.hasState()) {
            initialize();
        } else {
            state = Optional.empty();
        }
    }

    @Override
    public void addFood(final Food food, final Position position) {
        this.foodEnv.addFood(food, position);
    }

    @Override
    public void addNewTypeOfFood(final Food food) {
        this.foodManager.addFood(food);
        this.getInitialState().addFood(food);
    }

    @Override
    public List<Food> getExistingFoods() {
        return Collections.unmodifiableList(this.foodManager.getExistingFoods());
    }

    @Override
    public State getState() {
        return new StateImpl(this.foodEnv.getFoodsState(), this.bactManager.getBacteriaState());
    }

    @Override
    public void update() {
        this.updateBacteria();
        this.updateFood();
        this.getAnalysis().addState(this.getState());
        this.getAnalysis().setMutation(mutManager);
        ++this.iterations;
    }

    @Override
    public void addSpecies(final SpeciesOptions species) {
        speciesManager.addSpecies(species);
        this.getInitialState().addSpecies(species);
    }

    @Override
    public void setFoodDistributionStrategy(final DistributionStrategy strategy) {
        this.foodEnv.setDistributionStrategy(strategy);
    }

    @Override
    public boolean isSimulationOver() {
        return getState().getBacteriaState().isEmpty() || this.iterations == MAX_ITERATIONS;
    }

    @Override
    public void initialize() {
        try {
            Logger.getInstance().info("Environment", "Simulator initialized");
            if (this.getInitialState().hasState()) {
                state = Optional.of(getInitialState().getState().reconstructState(
                        s -> this.speciesManager.getSpeciesByName(s.getName()), () -> INITIAL_ENERGY));
                state.get().getFoodsState().forEach((position, food) -> this.foodEnv.addFood(food, position));
                this.bactManager.populate(Optional.of(this.state.get().getBacteriaState()));
            } else {
                this.bactManager.populate(Optional.empty());
            }
            this.getInitialState().setState(getState());
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("The initial state is inconsistent with the species of the environment");
        } catch (PositionAlreadyOccupiedException e) {
            throw new IllegalStateException("The initial state cannot be reconstructed correctly");
        }
    }
}
