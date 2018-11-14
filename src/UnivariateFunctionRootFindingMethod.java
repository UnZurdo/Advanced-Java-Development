/**
 * @param <T>
 */
public abstract class UnivariateFunctionRootFindingMethod<T extends Number> {
    /**
     *
     * @param x0
     * @param x1
     * @return
     * @throws IllegalArgumentException
     */
    public abstract T solve(T x0, T x1) throws IllegalArgumentException;

    /**
     * Informs the user of the time spent doing the calculations
     * @param x0, has to be a valid input
     * @param x1,
     * @throws IllegalArgumentException if
     */
    public void convergenceTime(T x0, T x1) throws IllegalArgumentException{
        // Start time
        long startTime = System.nanoTime();

        solve(x0, x1);

        long endTime = System.nanoTime();
        long diff = (endTime - startTime);  //divide by 1000000 to get milliseconds.

        System.out.printf("Convergence time: %d ms \n", diff / 1000);
    }

    /**
     *
     * @param x0
     * @param x1
     */
    public boolean reliability(T x0, T x1) throws  IllegalArgumentException{
        try {
            solve(x0, x1);
            //System.out.println("The method is guaranteed to find the root");
            return true;


        } catch (IllegalArgumentException e){
            //System.out.println("The method is NOT guaranteed to find the root \n -- IllegalArgumentException found --\n");
            //throw new IllegalArgumentException();
            return false;

        }

    }


}
