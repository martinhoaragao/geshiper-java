package Exceptions;

/**
 * Created by joaocosta on 11/06/15.
 */
public class InvalidMonthException extends Exception {
    /**
     * Unparameterized constructor
     */
    public InvalidMonthException () { super(); }

    /**
     * Parameterized constructor
     * @param info Additional information
     */
    public InvalidMonthException (String info) { super(info); }
}
