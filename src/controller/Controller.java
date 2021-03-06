package controller;

import java.io.IOException;

import view.View;

/**
 * Controller.
 * 
 *
 */
public interface Controller extends EnvironmentController {
    /**
     * Load an initial state of a simulation. Before loading, the view will be
     * notified that the state of the simulation is NOT_READY.
     * 
     * @param path
     *            the path of the file to load.
     * @throws IOException
     *             if any problem reading the file occurred.
     * @throws IllegalExtensionException
     *             if the extension of the file was not valid.
     * @throws FileFormatException
     *             if the extension of the file was not valid.
     */
    void loadInitialState(String path) throws IOException;

    /**
     * Save the initial state of the current simulation for rerunning.
     * 
     * @param path
     *            the path of the file to save into.
     * @throws IOException
     *             if any problem writing in the file occurred.
     */
    void saveInitialState(String path) throws IOException;

    /**
     * Load a replay of a simulation.
     * 
     * @param path
     *            the path of the file to load.
     * @throws IOException
     *             if any problem reading the file occurred.
     * @throws IllegalExtensionException
     *             if the extension of the file was not valid.
     * @throws FileFormatException
     *             if the extension of the file was not valid.
     */
    void loadReplay(String path) throws IOException;

    /**
     * Save the replay of the last runned simulation.
     * 
     * @param path
     *            the path of the file to save into.
     * @throws IOException
     *             if any problem writing in the file occurred.
     */
    void saveReplay(String path) throws IOException;

    /**
     * Save the final analysis of the last simulation runned.
     * 
     * @param path
     *            the path of the file to save into.
     * @throws IOException
     *             if any problem writing in the file occurred.
     */
    void saveAnalysis(String path) throws IOException;

    /**
     * Set the view of the simulation.
     * 
     * @param view
     *            the view to be linked to the controller
     */
    void setView(View view);
}
