package App_utils.math;

/**
 * Exception when a function requires a <strong>Max</strong> and <strong>Min</strong>
 * int bounds, but receives a min value that is actually bigger than the max boundary.
 */
public class InvertedMinAndMaxBoundException extends IllegalArgumentException{

    public InvertedMinAndMaxBoundException(){}

    public InvertedMinAndMaxBoundException(String message){
        super(message);
    }
}
