package view.view;

import java.awt.FlowLayout;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.View;
/**
 * 
 * Panel that contains all the functions on Bacterias and Foods.
 *
 */
public class BacteriaAndFoodPanel extends JPanel {
    private final JButton createFood = new JButton("Create Food");
    private final JButton createBacteria = new JButton("Create Bacteria");
    private final JLabel selectFood = new JLabel("Select Food: ");
    private final JComboBox<?> foods;
    /**
     * Automatically generated.
     */
    private static final long serialVersionUID = -239604088646327360L;
    /**
     * Construct the panel by passing the view on which to handle the interactions.
     * @param view the view on which to handle the interactions.
     */
    public BacteriaAndFoodPanel(final View view) {
        super();
        this.setLayout(new FlowLayout());
        this.foods = new JComboBox<>(view.getFoodsType().stream().map(f -> f.getName()).collect(Collectors.toList()).toArray());
        this.createBacteria.addActionListener(e -> {
            //TODO
        });
        this.createFood.addActionListener(e -> {
            //TODO
        });
        this.add(this.selectFood);
        this.add(this.foods);
        this.add(this.createBacteria);
        this.add(this.createFood);
        
    }
}
