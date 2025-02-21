package piggy.exceptions;

public class PiggyException extends Exception {
    public PiggyException(String message) {
        super(message); // Pass the error message to the superclass (Exception)
    }
}
