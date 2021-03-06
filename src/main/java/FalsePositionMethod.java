package main.java;

public class FalsePositionMethod extends UnivariateFunctionRootFindingMethod<Double> {
    public static final double EPSILON = 1.0E-6;
    private static final int MAX_ITERATIONS = 10;
    private Double root = 0.0;

    public FalsePositionMethod(UnivariateFunction<Double> f) {
        super(f);
    }

    @Override
    public Double solve(Double x0, Double x1) throws IllegalArgumentException, NullPointerException {

        if ( this.function.apply(x0) == null ) throw new NullPointerException("No univariate function is specified");

        int numberOfIterations = 0;

        if (this.function.apply(x0) * (this.function.apply(x1)) >= 0) {
            throw new IllegalArgumentException("f(" + x0 + ") = " + this.function.apply(x0) + " x f(" + x1 + ") = " + this.function.apply(x1) + " must be < 0");
        }

        boolean rootNotFound = true;

        do {
            root = (x0 * this.function.apply(x1) - x1 * this.function.apply(x0)) / (this.function.apply(x1) - this.function.apply(x0));
            numberOfIterations += 1;
            rootNotFound = (Math.abs(root - x1) > EPSILON) && (Math.abs(root - x0) > EPSILON);
            if (rootNotFound) {
                if (this.function.apply(root) * this.function.apply(x0) < 0) {
                    x1 = root;
                }
                else {
                    x0 = root;
                }
            }
        }
        while (rootNotFound && (numberOfIterations < MAX_ITERATIONS));

        // In case it can't converge to the root, means that the bracket contains a root
        if (rootNotFound && numberOfIterations == MAX_ITERATIONS) throw new IllegalArgumentException("Failed to converge to a root, invalid arguments");

        return root;
    }


    public double getAccuracy() {
        return Math.abs(this.function.apply(root));
    }

}