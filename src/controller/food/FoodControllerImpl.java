package controller.food;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Set;

import model.Environment;
import model.PositionImpl;
import model.food.ExistingFoodManager;
import utils.ConversionsUtil;
import view.model.ViewPosition;
import view.model.food.ViewFood;

/**
 * Implementation of FoodController, it manages food interactions.
 *
 */
public class FoodControllerImpl implements FoodController {
    private final Environment env;
    private final ExistingFoodManager manager;

    /**
     * Constructor that build the controller by passing the Environment on which it
     * must manage the foods.
     * 
     * @param env
     *            the environment on which the Controller must manage foods.
     */
    public FoodControllerImpl(final Environment env) {
        this.env = env;
        manager = env.getExistingFoods();
    }

    @Override
    public void addFoodFromViewToModel(final ViewFood food, final ViewPosition position) {
        this.env.addFood(ConversionsUtil.conversionFromViewToModel(food),
                new PositionImpl(position.getX(), position.getY()));
    }

    @Override
    public Set<ViewFood> getExistingViewFoods() {
        return Collections.unmodifiableSet(manager.getExistingFoodsSet().stream()
                .map(food -> ConversionsUtil.conversionFromModelToView(food)).collect(Collectors.toSet()));
    }

    @Override
    public void addNewTypeOfFood(final ViewFood food) {
        this.manager.addFood(ConversionsUtil.conversionFromViewToModel(food));
    }
}
