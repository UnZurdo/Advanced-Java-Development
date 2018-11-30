package main.java;

public class BisectionMethod extends UnivariateFunctionRootFindingMethod<Double> {
    public static final double EPSILON = 1.0E-6;
    private static final int MAX_ITERATIONS = 50;
    private Double root = 0.0;

    public BisectionMethod(UnivariateFunction<Double> f) {
        super(f);
    }

    @Override
    public Double solve(Double x0, Double x1) throws IllegalArgumentException, NullPointerException {
        // In case it can't converge to the root, means that the bracket contains a root
        if ( this.function.apply(x0) == null ) throw new NullPointerException("No univariate function is specified");

        int numberOfIterations = 0;
        Double m, y_m, y_x0;

        boolean rootNotFound = true;

        while ((x1 - x0) > EPSILON && (numberOfIterations < MAX_ITERATIONS)) {
            numberOfIterations += 1;

            m = (x0 + x1) / 2.0;
            y_m = this.function.apply(m);
            y_x0 = this.function.apply(x0);

            if ((y_m > 0 && y_x0 < 0) || (y_m < 0 && y_x0 > 0)) {
                x1 = m;
            } else {  // f(a) and f(m) have same signs: move a
                x0 = m;
            }
        }

        rootNotFound = (x1 - x0) > EPSILON;

        // In case it can't converge to the root, means that the bracket contains a root
        if ( rootNotFound ) throw new IllegalArgumentException("Failed to converge to a root, invalid arguments");

        this.root = (x0 + x1) / 2.0;

        return this.root;
    }
}
