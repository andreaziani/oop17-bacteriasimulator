package view.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ControllerImpl;
import view.View;
import view.ViewImpl;

/**
 * 
 * Main Frame of GUI.
 */
public class MainFrame extends JFrame {
    /**
     * Automatically generated.
     */
    private static final long serialVersionUID = -6602885048333089318L;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private final JPanel centerPanel = new JPanel(); //TODO pannello simulazione.
    private final int height = dim.height * 2 / 3;  // get 2/3 of the screen dimension.
    private final int width = dim.width * 2 / 3;    // get 2/3 of the screen dimension.
    /**
     * Constructor the MainFrame by passing a View.
     * @param view the View with which to interact.
     */
    public MainFrame(final View view) {
        super("Bacteria Simulator");
        final JPanel topPanel = new TopPanel(view);
        this.setSize(width, height);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    /**
     * Solo per testare.
     * @param args args.
     */
    public static void main(final String[] args) {
        new MainFrame(new ViewImpl(new ControllerImpl()));
    }
}
