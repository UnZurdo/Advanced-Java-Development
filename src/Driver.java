public class Driver {
    public static void main(String [] args) {

        final double LOWER = 0.5;
        final double HIGHER = 3.0;
        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);
        //UnivariateFunction<Double> f = x -> 3 * Math.pow(x, 2) * Math.cos(x) - 1.0 / 2.0;


        FalsePositionMethod solverFP = new FalsePositionMethod(f);
        BisectionMethod solverBS = new BisectionMethod(f);

        boolean reliableFP = solverFP.reliability(LOWER, HIGHER);
        boolean reliableBS = solverBS.reliability(LOWER, HIGHER);

        if(reliableFP){
            System.out.printf("FalsePositiveMethod is reliable for values %.2f, %.2f\n", LOWER, HIGHER);
            Double root_f1 = solverFP.solve(LOWER, HIGHER);
            System.out.println("root solverFP  at " + root_f1);
            Double accuracy = solverFP.getAccuracy();
            System.out.println("Accuracy solverFP  at " + accuracy);

            long diff = solverFP.convergenceTime(LOWER, HIGHER);
            System.out.printf("Convergence time: %d ms \n", diff);


        }
        else {
            System.out.printf("FalsePositiveMethod is NOT reliable for values %.2f, %.2f \n", LOWER, HIGHER);
        }

        if(reliableBS){
            System.out.printf("BisectionMethod is reliable for values %.2f, %.2f\n", LOWER, HIGHER);
            Double root_f2 = solverBS.solve(LOWER, HIGHER);
            System.out.println("root solverBS at " + root_f2);
            long diff = solverBS.convergenceTime(LOWER, HIGHER);
            System.out.printf("Convergence time: %d ms \n", diff);


        }
        else {
            System.out.printf("BisectionMethod is NOT reliable for values %.2f, %.2f \n", LOWER, HIGHER);
        }

    }
}
