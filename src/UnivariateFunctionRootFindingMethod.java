/**
 * Interface to solve univariate functions
 * @param <T>, generic variable that supports numeric values
 */
public interface UnivariateFunctionRootFindingMethod<T extends Number> {
    /**
     * Root-finding function for univariate functions
     * @param x0 numeric value (integer or decimal), lower bound of the bracket
     * @param x1 numeric value (integer or decimal) upper bound of the bracket
     * @return numeric value with the result of calculating the root
     * @throws IllegalArgumentException if the bracket (x0, x1) in the given univariate function does not contain a root
     */
    T solve(T x0, T x1) throws IllegalArgumentException;

    /**
     * Informs the user of the time spent doing the calculations
     * @param x0 numeric value (integer or decimal), lower bound of the bracket
     * @param x1 numeric value (integer or decimal) upper bound of the bracket
     * @return numeric long value, time spent computing the root
     * @throws IllegalArgumentException if the bracket (x0, x1) does not contain a root
     */
    default long convergenceTime(T x0, T x1) throws IllegalArgumentException{
        // Start time
        long startTime = System.nanoTime();

        solve(x0, x1);

        long endTime = System.nanoTime();
        long diff = (endTime - startTime);  //divide by 1000000 to get milliseconds.

        //System.out.printf("Convergence time: %d ms \n", diff / 1000);
        return diff / 1000;
    }


    /**
     * @param x0 numeric value (integer or decimal), lower bound of the bracket
     * @param x1 numeric value (integer or decimal) upper bound of the bracket
     * @return true if the function can be solved (no IllegalArgumentException exception), otherwise return false
     */
    default boolean reliability(T x0, T x1) {
        try {
            solve(x0, x1);
            return true;

        } catch (IllegalArgumentException e){
            return false;

        }
    }


}
