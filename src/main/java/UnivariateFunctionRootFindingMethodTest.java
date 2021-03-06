package main.java;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Does main.java.BisectionMethod throw an exception ???

class UnivariateFunctionRootFindingMethodTest {

    private UnivariateFunctionRootFindingMethod<Double> univariateMethods;
    private BisectionMethod BM, BM2;
    private FalsePositionMethod FPM, FPM2;

    @BeforeEach
    public void initSolver() {
        UnivariateFunction<Double> f2 = x -> x - 2.5 * Math.sin(x) * Math.sin(x);
        UnivariateFunction<Double> f = x -> 3 * Math.pow(x, 2) * Math.cos(x) - 1.0 / 2.0;
        BM = new BisectionMethod(f);
        FPM = new FalsePositionMethod(f);
        BM2 = new BisectionMethod(f2);
        FPM2 = new FalsePositionMethod(f2);
    }

    // Tests for the main.java.FalsePositionMethod implementation of main.java.UnivariateFunctionRootFindingMethod
    @ParameterizedTest
    @CsvSource({ "1.0, 4.0, 2.0228952451909037", "0.5, 3.0, 2.0228952407765113"})
    void solveFMP(double x0, double x1, double solution) {
        double result = FPM.solve(x0, x1);
        assertEquals(result, solution);
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
        assertFalse(FPM.reliability(x0, x1));
        System.out.printf("-> Test notReliableFPM passed with values %.2f, %.2f \n", x0, x1);

    }

    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void reliableFPM(double x0, double x1) {
        assertTrue(FPM.reliability(x0, x1));
        System.out.printf("-> Test reliableFPM passed with values %.2f, %.2f \n", x0, x1);

    }

    // Tests for the main.java.BisectionMethod implementation of main.java.UnivariateFunctionRootFindingMethod





}