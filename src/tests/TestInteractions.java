package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Dimension;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import controller.Controller;
import controller.ControllerImpl;
import model.AlreadyExistingFoodException;
import model.PositionAlreadyOccupiedException;
import model.food.Nutrient;
import view.controller.ViewController;
import view.controller.ViewImpl;
import view.model.ViewPosition;
import view.model.ViewPositionImpl;
import view.model.food.ViewFood;
import view.model.food.CreationViewFoodImpl.ConcreteViewFoodBuilder;
/**
 * TestClass for interaction of the user adding foods.
 *
 */
public class TestInteractions {
    private static final int WITDH = 1920;
    private static final int HEIGHT = 1080; 
    private final Controller controller = new ControllerImpl();
    private final ViewController view = new ViewImpl(this.controller);
    private final ViewPosition p1 = new ViewPositionImpl(1.0, 2.0);
    private final ViewPosition p2 = new ViewPositionImpl(10.0, 20.0);
    private final ViewPosition p3 = new ViewPositionImpl(50.0, 50.0);
    private ViewFood creationOfFood(final String name, final Pair<Nutrient, Double> pair) {
        return new ConcreteViewFoodBuilder(name).addNutrient(pair).build();
    }

    /**
     * Testing food creation from view.
     */
    @Test
    public void testCreation() {
        this.view.getController().addNewTypeOfFood(creationOfFood("Banana", Pair.of(Nutrient.CARBOHYDRATES, 1.0)));
        assertEquals("There is only one type of food", this.controller.getExistingViewFoods().size(), 1);
        assertEquals("There is one type of food in each set", this.view.getFoodTypes().size(), this.controller.getExistingViewFoods().size());
        this.view.getController().addNewTypeOfFood(creationOfFood("Fragola", Pair.of(Nutrient.CARBOHYDRATES, 1.0)));
        this.view.getController().addNewTypeOfFood(creationOfFood("Mela", Pair.of(Nutrient.WATER, 1.0)));
        assertEquals("There are three types of food", this.controller.getExistingViewFoods().size(), 3);
        assertThrows(AlreadyExistingFoodException.class, () -> this.view.getController().addNewTypeOfFood(creationOfFood("Mela", Pair.of(Nutrient.WATER, 1.0))));
        this.view.getController().addNewTypeOfFood(creationOfFood("banana", Pair.of(Nutrient.CARBOHYDRATES, 1.0)));
        assertEquals("banana is different from Banana", this.controller.getExistingViewFoods().size(), 4);
    }
    /**
     * Testing food insertion from view.
     */
    @Test
    public void testInsertion() {
        this.view.getController().addFoodFromView(creationOfFood("Fragola", Pair.of(Nutrient.CARBOHYDRATES, 1.0)), p1);
        this.view.getController().addFoodFromView(creationOfFood("Mela", Pair.of(Nutrient.WATER, 2.0)), p2);
        this.view.getController().addFoodFromView(creationOfFood("Fragola", Pair.of(Nutrient.PEPTONES, 1.0)), p3);
        assertThrows(PositionAlreadyOccupiedException.class, () -> this.view.getController().addFoodFromView(creationOfFood("Pera", Pair.of(Nutrient.CARBOHYDRATES, 1.0)),
                                                                                     new ViewPositionImpl(1.0, 2.0)));
    }
    /**
     * Setting the dimension of the view.
     */
    @Before
    public void setDimension() {
        this.view.setDimension(new Dimension(WITDH, HEIGHT));
    }
}
