package model;

import java.util.Random;

import model.geneticcode.Gene;
import model.geneticcode.NucleicAcid;

/**
 * 
 * Implementation of Mutation.
 *
 */

public class MutationImpl implements Mutation {

    private static final int LIMIT_MUTATION = 10000;
    private static final int RANGE = 10;
    private static final int ZONE_ACTIONS = 1;
    private static final int ZONE_SPEED = 4;
    private static final int ZONE_NUTRIENTS = 7;
    private static final int ZONE_51 = 10;
    private final Gene code;
    private int pos;
    private int na;
    private int zone;
    private int naMutate;
    private int randomMutation;

    /**
     * Construct a Bacteria Mutation.
     * 
     * @param code
     *            the code of Gene.
     */
    public MutationImpl(final Gene code) {
        this.code = code;
    }

    private int checkMutation() {
        final Random rndMt = new Random();
        return rndMt.nextInt(RANGE);
    }
    private boolean possibilityOfMutation() {
        this.randomMutation = this.randomMutation + checkMutation();
        return this.randomMutation >= LIMIT_MUTATION;
    }

    private int randomPos() {
        final Random rndPos = new Random();
        this.pos = rndPos.nextInt(3);
        return this.pos;
    }

    private int randomNucleicAcid() {
        final Random randNa = new Random();
        this.na = randNa.nextInt(NucleicAcid.values().length);
        return this.na;
    }

    private int randomZone() {
        final Random randomZone = new Random();
        this.zone = randomZone.nextInt(4);
        return zone;
    }

    @Override
    public final void alteratedCode() {
        if (possibilityOfMutation()) {
            this.pos = randomPos();
            this.na = randomNucleicAcid();
            this.naMutate = randomNucleicAcid();
            this.zone = randomZone();
            this.randomMutation = 0;
            while (this.code.getCode().get(0).equals(NucleicAcid.values()[this.naMutate])) {
                this.naMutate = randomNucleicAcid();
            }
            this.code.getCode().set(0, NucleicAcid.values()[this.naMutate]);
            switch (this.zone) {
                case 1:
                    this.pos += ZONE_ACTIONS;
                    this.code.getCode().set(this.pos, NucleicAcid.values()[this.na]);
                    break;
                case 2:
                    this.pos += ZONE_SPEED;
                    this.code.getCode().set(this.pos, NucleicAcid.values()[this.na]);
                    break;
                case 3:
                    this.pos += ZONE_NUTRIENTS;
                    this.code.getCode().set(this.pos, NucleicAcid.values()[this.na]);
                    break;
                case 4:
                    this.pos += ZONE_51;
                    this.code.getCode().set(this.pos, NucleicAcid.values()[this.na]);
                    break;
                default:
                    this.code.getCode();
            }
        }
    }
}
