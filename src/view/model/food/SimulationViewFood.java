package view.model.food;

import java.awt.Color;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import model.food.Nutrient;

/**
 * 
 * The class representing a food in the view.
 *
 */
public class SimulationViewFood implements ViewFood {
    private final Optional<String> name;
    private final Color color;
    private final Map<Nutrient, Double> nutrients;

    /**
     * 
     * @param name
     *            an optional containing the name of the food.
     * @param color
     *            the color of the food.
     * @param nutrients
     *            the nutrients in the food in their quantity.
     */
    public SimulationViewFood(final Optional<String> name, final Color color, final Map<Nutrient, Double> nutrients) {
        this.name = name;
        this.color = color;
        this.nutrients = nutrients;
    }

    @Override
    public String getName() {
        return name.get();
    }
    /**
     * 
     * @return true if the name is present, false in other case.
     */
    public boolean isNamePresent() {
        return this.name.isPresent();
    }
    @Override
    public Set<Nutrient> getNutrients() {
        return this.nutrients.keySet();
    }

    @Override
    public double getQuantityFromNutrient(final Nutrient nutrient) {
        return this.nutrients.get(nutrient);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

}
