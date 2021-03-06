package model.geneticcode;

/**
 * Interface of a part of GeneticCode. It represent the speed of bacteria.
 */
public interface SpeedGene {
    /**
     * Gene interprets part of the DNA code.
     * eg. ""AAA" "AAT" "AAC" "AAG".
     * @return an interpretation of DNA.
     */
    double interpretSpeed();
}
