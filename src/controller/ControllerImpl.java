package controller;

import java.io.IOException;

import view.View;
import view.model.ViewPosition;
import view.model.food.ViewFood;

/**
 * Controller implementation.
 *
 */
public final class ControllerImpl extends EnvironmentControllerImpl implements Controller {
    private final FileController fileController;
    private View view;

    /**
     * Create a controller implementation.
     */
    public ControllerImpl() {
        super();
        fileController = new FileControllerImpl();
    }

    @Override
    public synchronized void simulationLoop() {
        this.view.update(this.getState());
    }

    @Override
    public synchronized void setView(final View view) {
        this.view = view;
    }

    @Override
    public synchronized void loadInitialState(final String path) throws IOException {
        this.updateCurrentState(SimulationCondition.PAUSED, SimulationMode.REPLAY);
        this.setInitialState(this.fileController.loadInitialState(path));
    }

    @Override
    public synchronized void saveInitialState(final String path) throws IOException {
        this.fileController.saveInitialState(path, this.getInitialState());
    }

    @Override
    public synchronized void loadReplay(final String path) throws IOException {
        this.startReplay(this.fileController.loadReplay(path));
    }

    @Override
    public synchronized void saveReplay(final String path) throws IOException {
        this.fileController.saveReplay(path, this.getReplay());
    }

    @Override
    public synchronized void saveAnalysis(final String path) throws IOException {
        this.fileController.saveAnalysis(path, this.getAnalysis());
    }

    @Override
    public synchronized void updateCurrentState(final SimulationCondition condition, final SimulationMode mode) {
        super.updateCurrentState(condition, mode);
        this.view.updateSimulationState(this.getCurrentState());
    }

    @Override
    public synchronized void addFoodFromView(final ViewFood food, final ViewPosition position) {
        super.addFoodFromView(food, position);
        if (this.view != null) {
            this.view.update(this.getState());
        }
    }
}
