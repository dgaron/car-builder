package car_builder;

/**
 * The NegativeTradeException class provides a custom error message for
 * negative values entered into the trade-in value field of the CarBuilder program.
 */
public class NegativeTradeException extends Exception {
    /**
     * This no-arg constructor passes a customized error message to the superclass constructor.
     */
    public NegativeTradeException()
    {
        super("Error: Trade-in value cannot be negative");
    }
}
