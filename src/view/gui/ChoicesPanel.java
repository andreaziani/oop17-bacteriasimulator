package view.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.SimulationCondition;
import controller.SimulationState;
import view.SimulationStateUpdatable;
import view.controller.ViewController;

/**
 * Panel that contains all the components that allow the user to choose whether
 * to start and stop the simulation.
 *
 */
public class ChoicesPanel extends JPanel implements SimulationStateUpdatable {
    private final JButton startSimulation = new JButton("Start");
    private final JButton resetSimulation = new JButton("Reset");
    private final JButton stopSimulation = new JButton("Stop");
    private final JButton pauseSimulation = new JButton("Pause");
    /**
     * Automatically generated.
     */
    private static final long serialVersionUID = -5569246934783157059L;

    /**
     * Construct the panel by passing the view on which to handle the interactions.
     * 
     * @param view
     *            the view controller on which to handle the interactions.
     * @param main
     *            the main frame of the application.
     */
    public ChoicesPanel(final ViewController view, final UserInterface main) {
        super();
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.startSimulation.addActionListener(e -> {
            if (view.getController().getCurrentState().getCurrentCondition() != SimulationCondition.PAUSED) { 
                SwingUtilities.invokeLater(() -> view.getController().start());
            } else {
                SwingUtilities.invokeLater(() -> view.getController().resume());
            }
        });
        this.resetSimulation.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                view.getController().resetSimulation();
                main.notifyUpdate();
            });
        });
        this.stopSimulation.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> view.getController().stop());
        });
        this.pauseSimulation.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> view.getController().pause());
        });

        this.add(this.resetSimulation);
        this.add(this.startSimulation);
        this.add(this.pauseSimulation);
        this.add(this.stopSimulation);
        this.startSimulation.setEnabled(false);
        this.stopSimulation.setEnabled(false);
        this.pauseSimulation.setEnabled(false);
        this.setOpaque(true);
        this.startSimulation.setFont(GuiUtils.FONT);
        this.resetSimulation.setFont(GuiUtils.FONT);
        this.stopSimulation.setFont(GuiUtils.FONT);
        this.pauseSimulation.setFont(GuiUtils.FONT);
    }

    @Override
    public final void updateSimulationState(final SimulationState state) {
        SwingUtilities.invokeLater(() -> {
            switch (state.getCurrentCondition()) {
            case RUNNING:
                startSimulation.setEnabled(false);
                stopSimulation.setEnabled(true);
                pauseSimulation.setEnabled(true);
                resetSimulation.setEnabled(false);
                break;
            case READY:
            case PAUSED:
                startSimulation.setEnabled(true);
                pauseSimulation.setEnabled(false);
                resetSimulation.setEnabled(true);
                break;
            default:
                startSimulation.setEnabled(false);
                stopSimulation.setEnabled(false);
                pauseSimulation.setEnabled(false);
                resetSimulation.setEnabled(true);
                break;
            }
            if (state.getCurrentCondition() == SimulationCondition.PAUSED) {
                this.startSimulation.setText("Resume");
                stopSimulation.setEnabled(true);
            } else {
                this.startSimulation.setText("Start");
            }
        });
    }
}
