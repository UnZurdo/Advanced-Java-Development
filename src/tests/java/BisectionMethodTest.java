package tests.java;

import main.java.BisectionMethod;
import main.java.UnivariateFunction;
import main.java.UnivariateFunctionRootFindingMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Does main.java.BisectionMethod throw an exception ???

class BisectionMethodTest {
    private static final double DELTA = 1e-6;

    private UnivariateFunctionRootFindingMethod<Double> univariateMethods;
    private BisectionMethod BM, BM2;

    @BeforeEach
    public void initSolver() {
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);
        UnivariateFunction<Double> f2 = x -> 3 * Math.pow(x, 2) * Math.cos(x) - 1.0 / 2.0;
        BM = new BisectionMethod(f);
        BM2 = new BisectionMethod(f2);
    }

    // No univariate function is specified
    @Test
    void noUnivariateFunctionExceptionBM() {
        UnivariateFunction<Double> fTest = x -> null;
        BisectionMethod solverTest = new BisectionMethod(fTest);

        Executable testCode = () -> solverTest.solve(1.0, 2.0);
        assertThrows(NullPointerException.class, testCode, "No univariate function is specified");
        System.out.printf("Test noUnivariateFunctionExceptionBM passed");
    }

    // Tests for the main.java.FalsePositionMethod implementation of main.java.UnivariateFunctionRootFindingMethod
    @ParameterizedTest
    @CsvSource({ "1.0, 4.0, 2.0228952451909037", "0.5, 3.0, 2.0228952407765113"})
    void solveFBM(double x0, double x1, double solution) {
        double result = BM.solve(x0, x1);
        assertEquals(result, solution, DELTA);
        System.out.printf("-> Test solveBM passed with values %.2f, %.2f and solution %f\n", x0, x1, solution);

    }

    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void solveAcceptsLegalArgumentsBM(double x0, double x1) {
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);
        Executable testCode = () -> BM.solve(x0, x1);
        assertDoesNotThrow(testCode);
        System.out.printf("-> Test solveAcceptsLegalArgumentsBM passed with values %.2f, %.2f \n", x0, x1);
    }


    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void reliableBM(double x0, double x1) {
        Assertions.assertTrue(BM.reliability(x0, x1));
        System.out.printf("-> Test reliableBM passed with values %.2f, %.2f \n", x0, x1);

    }


}