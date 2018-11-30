package tests.java;

import main.java.FalsePositionMethod;
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

class FalsePositionMethodTest {
    private static final double DELTA = 1e-6;

    private UnivariateFunctionRootFindingMethod<Double> univariateMethods;
    private FalsePositionMethod FPM, FPM2;

    @BeforeEach
    public void initSolver() {
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);
        UnivariateFunction<Double> f2 = x -> 3 * Math.pow(x, 2) * Math.cos(x) - 1.0 / 2.0;
        FPM = new FalsePositionMethod(f);
        FPM2 = new FalsePositionMethod(f2);
    }

    // No univariate function is specified
    @Test
    void noUnivariateFunctionExceptionFMP() {
        UnivariateFunction<Double> fTest = x -> null;
        FalsePositionMethod solverTest = new FalsePositionMethod(fTest);

        Executable testCode = () -> solverTest.solve(1.0, 2.0);
        assertThrows(NullPointerException.class, testCode, "No univariate function is specified");
        System.out.printf("Test noUnivariateFunctionExceptionFMP passed");
    }

    // Tests for the main.java.FalsePositionMethod implementation of main.java.UnivariateFunctionRootFindingMethod
    @ParameterizedTest
    @CsvSource({ "1.0, 4.0, 2.0228952451909037", "0.5, 3.0, 2.0228952407765113"})
    void solveFMP(double x0, double x1, double solution) {
        double result = FPM.solve(x0, x1);
        assertEquals(result, solution, DELTA);
        System.out.printf("-> Test solveFMP passed with values %.2f, %.2f and solution %f\n", x0, x1, solution);

    }

    @ParameterizedTest
    @CsvSource({ "-1.0, -3.0", "0.0, 0.5"})
    public void solveRejectsInvalidArgumentsFPM(double x0, double x1) {
        Executable testCode = () -> FPM.solve(x0, x1);
        assertThrows(IllegalArgumentException.class, testCode, "Invalid Arguments");
        System.out.printf("-> Test solveRejectsInvalidArgumentsFPM passed with values %.2f, %.2f \n", x0, x1);

    }
    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void solveAcceptsLegalArgumentsFPM(double x0, double x1) {
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);
        Executable testCode = () -> FPM.solve(x0, x1);
        assertDoesNotThrow(testCode);
        System.out.printf("-> Test solveAcceptsLegalArgumentsFPM passed with values %.2f, %.2f \n", x0, x1);
    }

    @ParameterizedTest
    @CsvSource({ "-1.0, -3.0"})
    void convergenceTime(double x0, double x1) {
        Executable testCode = () -> FPM.convergenceTime(x0, x1);
        assertThrows(IllegalArgumentException.class, testCode, "Invalid Arguments");
    }


    @ParameterizedTest
    @CsvSource({ "-1.0, -3.0", "0.0, 0.5"})
    public void notReliableFPM(double x0, double x1) {
        Assertions.assertFalse(FPM.reliability(x0, x1));
        System.out.printf("-> Test notReliableFPM passed with values %.2f, %.2f \n", x0, x1);

    }

    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void reliableFPM(double x0, double x1) {
        Assertions.assertTrue(FPM.reliability(x0, x1));
        System.out.printf("-> Test reliableFPM passed with values %.2f, %.2f \n", x0, x1);

    }



}