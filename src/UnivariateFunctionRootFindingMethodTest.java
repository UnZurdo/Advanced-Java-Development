import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Does BisectionMethod throw an exception ???

class UnivariateFunctionRootFindingMethodTest {

    private UnivariateFunctionRootFindingMethod<Double> univariateMethods;
    private BisectionMethod BM;
    private FalsePositionMethod FPM;

    @BeforeEach
    public void initSolver() {
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);

        BM = new BisectionMethod(f);
        FPM = new FalsePositionMethod(f);
        //univariateMethods = new UnivariateFunctionRootFindingMethod<Double>();
    }

    @Test
    void solve() {

    }

    @ParameterizedTest
    @CsvSource({ "-1.0, -3.0", "0.0, 0.5"})
    public void solveRejectsInvalidArguments(double x0, double x1) {
        Executable testCode = () -> FPM.solve(x0, x1);
        assertThrows(IllegalArgumentException.class, testCode, "Invalid Arguments");
        System.out.printf("-> Test solveRejectsInvalidArguments passed with values %.2f, %.2f \n", x0, x1);

    }
    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void solveAcceptsLegalArguments(double x0, double x1) {
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);

        Executable testCode = () -> FPM.solve(x0, x1);
        Executable testConstructor = () ->   new FalsePositionMethod(f) ;

        assertDoesNotThrow(testCode);
        System.out.printf("-> Test solveAcceptsLegalArguments passed with values %.2f, %.2f \n", x0, x1);
    }

    @Test
    void convergenceTime() {
    }


    @ParameterizedTest
    @CsvSource({ "-1.0, -3.0", "0.0, 0.5"})
    public void notReliableFPM(double x0, double x1) {
        assertFalse(FPM.reliability(x0, x1));
        System.out.printf("-> Test notReliableFPM passed with values %.2f, %.2f \n", x0, x1);

    }

    @ParameterizedTest
    @CsvSource({ "1.0, 3.0", "0.25, 0.5", "-2.5, 5.5"})
    public void rReliableFPM(double x0, double x1) {
        assertTrue(FPM.reliability(x0, x1));
        System.out.printf("-> Test reliableFPM passed with values %.2f, %.2f \n", x0, x1);

    }
}