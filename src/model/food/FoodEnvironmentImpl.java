package model.food;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Position;
import utils.PositionAlreadyOccupiedException;

/** 
 * Implementation of FoodEnvironment, contains information
 * about foods (Food's Position).
 *
 *
 */
public class FoodEnvironmentImpl implements FoodEnvironment {
    private static final int MAXATTEMPS = 10;
    private final Map<Position, Food> foods = new HashMap<>();

    @Override
    public void addFood(final Food food, final Position position) {
        if (!this.foods.containsKey(position)) {
            this.foods.put(position, food);
        } else {
            throw new PositionAlreadyOccupiedException();
        }
    }
    @Override
    public void removeFood(final Food food, final Position position) {
        if (this.foods.containsKey(position) && this.foods.get(position).equals(food)) {
            this.foods.remove(position);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public Map<Position, Food> getFoodsState() {
        return Collections.unmodifiableMap(this.foods);
    }
    @Override
    public void changeFoodPosition(final Position oldPosition, final Position newPosition, final Food food) {
       removeFood(food, oldPosition); //maybe they can generate an Exception.
       addFood(food, newPosition);
    }
    @Override
    public void addRandomFood(final ExistingFoodManager manager) {
        boolean check = true;
        final RandomFoodStrategy foodStrategy = new RandomFoodStrategyImpl();
        final RandomPositionStrategy positionStrategy = new ExponentialDistribuitionStrategyImpl();
        for (int i = MAXATTEMPS; (i > 0 && check); i--) {
            try {
                final List<Food> list = manager.getExistingFoodsSet().stream().collect(Collectors.toList());
                addFood(foodStrategy.getFood(manager), positionStrategy.getPosition());
                check = false;
            } catch (PositionAlreadyOccupiedException e) {
                check = true;
            }
        }
    }

}