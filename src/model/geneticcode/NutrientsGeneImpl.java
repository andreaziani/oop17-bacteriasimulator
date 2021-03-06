package model.geneticcode;

import java.util.ArrayList;
import java.util.List;

import model.Energy;
import model.EnergyImpl;
import model.food.Nutrient;

/**
 * Implementation of interface NutrientsGene.
 */
public class NutrientsGeneImpl implements NutrientsGene {

    private final Gene code;
    private final List<Integer> list;
    private static final int VAR_CARBOHYDRATES = 3;
    private static final int VAR_WATER = 1;
    private static final int VAR_PEPTONES = 8;
    private static final int VAR_HYDROLYSATES = 5;
    private static final int VAR_INORGANIC_SALT = 1;
    private static final int MIN_ZONE = 7;
    private static final int MAX_ZONE = 10;
    private double en;

    /**
     * Construct a SpeedGene of GeneticCode.
     * 
     * @param code
     *          the code of DNA.
     */
    public NutrientsGeneImpl(final Gene code) {
        this.code = code;
        this.list = new ArrayList<>();
        for (int i = MIN_ZONE; i < MAX_ZONE; i++) {
            this.list.add(i);
        }
    }

    @Override
    public final Energy interpretNutrients(final Nutrient nutrient) {
        if (nutrient.equals(Nutrient.CARBOHYDRATES)) {
            this.en = code.interpret(list, VAR_CARBOHYDRATES);
        } else if (nutrient.equals(Nutrient.HYDROLYSATES)) {
            this.en = code.interpret(list, VAR_HYDROLYSATES);
        } else if (nutrient.equals(Nutrient.WATER)) {
            this.en = code.interpret(list, VAR_WATER);
        } else if (nutrient.equals(Nutrient.PEPTONES)) {
            this.en = code.interpret(list, VAR_PEPTONES);
        } else if (nutrient.equals(Nutrient.INORGANIC_SALT)) {
            this.en = code.interpret(list, VAR_INORGANIC_SALT);
        } else {
            throw new IllegalArgumentException("error type of nutrient");
        }
        return new EnergyImpl(en);
    }
}
