package controller;

import java.io.File;
import java.io.IOException;

/**
 * Controller implementation.
 *
 */
public class ControllerImpl extends EnvironmentControllerImpl implements Controller {
    private static final Controller SINGLETON = new ControllerImpl();
    private final FileController fileController;

    private ControllerImpl() {
        super();
        fileController = new FileControllerImpl();
    }

    /**
     * Get the reference to the Controller.
     * @return the controller
     */
    public static Controller getController() {
        return SINGLETON;
    }

    @Override
    public void loadInitialState(final File file) throws IOException {
        this.setInitialState(this.fileController.loadInitialState(file));
    }

    @Override
    public void saveInitialState(final File file) throws IOException {
        this.fileController.saveInitialState(file, this.getInitialState());
    }

    @Override
    public void loadReplay(final File file) {
        this.fileController.loadReplay(file);
    }

    @Override
    public void saveReplay(final File file) {
        this.fileController.saveReplay(file, this.getReplay());
    }

    @Override
    public void saveAnalisys(final File file) throws IOException {
        this.fileController.saveAnalisys(file, this.getAnalysis());
    }
}
