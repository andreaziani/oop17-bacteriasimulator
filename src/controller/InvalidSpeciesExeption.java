package controller;

/**
 * An exception signaling the attempt to create an invalid Species.
 */
public class InvalidSpeciesExeption extends RuntimeException {

    /**
     * Automatically generated.
     */
    private static final long serialVersionUID = 1543842807214081747L;

    /**
     * Construct a new InvalidSpeciesExeption with a given error message.
     * 
     * @param message
     *            the error message.
     */
    public InvalidSpeciesExeption(final String message) {
        super(message);
    }
}
