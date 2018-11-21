/**
 * Interface to solve univariate functions
 * @param <T>, generic variable that supports numeric values
 */
public abstract class UnivariateFunctionRootFindingMethod<T extends Number> {

    public UnivariateFunction<T> function;

    /**
     * @throws NullPointerException if No univariate function is specified
     */
    UnivariateFunctionRootFindingMethod() throws NullPointerException {
        throw new NullPointerException("No univariate function is specified");
    }

    /**
     * @param function is a univariate function
     */
    UnivariateFunctionRootFindingMethod(UnivariateFunction<T> function){
        this.function = function;
    }

    /**
     * Root-finding function for univariate functions
     * @param x0 numeric value (integer or decimal), lower bound of the bracket
     * @param x1 numeric value (integer or decimal) upper bound of the bracket
     * @return numeric value with the result of calculating the root
     * @throws IllegalArgumentException if the bracket (x0, x1) in the given univariate function does not contain a root
     * @throws ArithmeticException if there is a failure to converge to a root.
     */
    public abstract T solve(T x0, T x1) throws IllegalArgumentException, ArithmeticException;

    /**
     * Informs the user of the time spent doing the calculations
     * @param x0 numeric value (integer or decimal), lower bound of the bracket
     * @param x1 numeric value (integer or decimal) upper bound of the bracket
     * @return numeric long value, time spent computing the root
     * @throws IllegalArgumentException if the bracket (x0, x1) does not contain a root
     * @throws ArithmeticException if there is a failure to converge to a root.
     */
    public  long convergenceTime(T x0, T x1) throws IllegalArgumentException, ArithmeticException{
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
    public boolean reliability(T x0, T x1) {
        try {
            solve(x0, x1);
            return true;

        } catch (IllegalArgumentException e){
            return false;

        }
    }


}
