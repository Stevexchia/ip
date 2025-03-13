package piggy.exceptions;

/**
 * Represents an exception specific to the Piggy application.
 */
public class PiggyException extends Exception {
    public PiggyException(String message) {
        super(message); // Pass the error message to the superclass (Exception)
    }
}
