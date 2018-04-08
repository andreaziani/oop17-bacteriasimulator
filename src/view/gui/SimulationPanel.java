package view.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
//import java.awt.Color;
//import java.awt.Point;
//import java.util.HashMap;
//import java.util.Map;
import java.util.Optional;

import javax.swing.JPanel;

import utils.Log;
import view.model.ViewState;

/**
 * Panel that represent the running simulation.
 *
 */
public class SimulationPanel extends JPanel {
    // private static final int RADIUS = 15;
    // private final Map<Point, Color> foods = new HashMap<>();
    /**
     * Automatically generated.
     */
    private static final long serialVersionUID = 2015198232069587535L;
    private Optional<ViewState> state = Optional.empty();

    /**
     * 
     * @param width
     *            the max width of the panel.
     * @param height
     *            the max height of the panel.
     */
    public SimulationPanel(final int width, final int height) {
        super();
        this.setSize(width, height);
        this.setLayout(new FlowLayout());
        this.setOpaque(true);
        this.setBackground(Color.WHITE);
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (state.isPresent()) {
            state.get().getBacteriaState().entrySet().stream().forEach(e -> {
                Graphics2D g2d = (Graphics2D)g;
                // Assume x, y, and diameter are instance variables.
                //g.drawOval((int) e.getKey().getX(), (int) e.getKey().getY(), e.getValue().getRadius().getXRadius() + 5, e.getValue().getRadius().getYRadius() + 5);
                // TODO probably there is a better way
                Ellipse2D.Double circle = new Ellipse2D.Double((int) e.getKey().getX(), (int) e.getKey().getY(), e.getValue().getRadius().getXRadius(),
                        e.getValue().getRadius().getYRadius());
                g.setColor(e.getValue().getColor());
                g2d.fill(circle);
            });

            state.get().getFoodsState().entrySet().stream().forEach(e -> {
                g.setColor(e.getValue().getColor());
                g.fillRect((int) e.getKey().getX(), (int) e.getKey().getY(), e.getValue().getRadius().getXRadius(),
                        e.getValue().getRadius().getYRadius()+2);
            });
            System.out.println("Bacteria size = " + state.get().getBacteriaState().size() );
            state.get().getBacteriaState().entrySet().stream()
                                        .limit(1)
                                        .forEach(e -> Log.getLog().info("Bacteria radius: " + e.getValue().getRadius().getXRadius() + ", " 
                                                                                            + e.getValue().getRadius().getYRadius()));
        }
        // super.paintComponent(g);
        // for (final Map.Entry<Point, Color> e : this.foods.entrySet()) {
        // g.setColor(e.getValue());
        // g.fillOval(e.getKey().x, e.getKey().y, RADIUS * 2, RADIUS * 2);
        // }
    }

    /**
     * Set the state of the objects in simulation panel.
     * 
     * @param state
     *            the state of the objects in the simulation.
     */
    public void setState(final ViewState state) {
        this.state = Optional.of(state);
    }
}
