public class Driver {
    public static void main(String [] args) {

        final double LOWER = 1.0;
        final double HIGHER = 5.0;

/*        UnivariateFunction<Double> f = new UnivariateFunction<Double> () {
            @Override
            public Double apply(Double x) {
                return x - 2.5 * Math.sin(x) * Math.sin(x);
            }
        };*/

        UnivariateFunction<Double> f = x -> x - 2.5 * Math.sin(x) * Math.sin(x);


        FalsePositionMethod solverFP = new FalsePositionMethod(f);
        BisectionMethod solverBS = new BisectionMethod(f);


        boolean reliableFP = solverFP.reliability(LOWER, HIGHER);
        boolean reliableBS = solverBS.reliability(LOWER, HIGHER);

        if(reliableFP){
            System.out.printf("FalsePositiveMethod is reliable for values %.2f, %.2f\n", LOWER, HIGHER);
            Double root_f1 = solverFP.solve(LOWER, HIGHER);
            System.out.println("root solverFP  at " + root_f1);
            solverFP.convergenceTime(LOWER, HIGHER);


        }
        else {
            System.out.printf("FalsePositiveMethod is NOT reliable for values %.2f, %.2f \n", LOWER, HIGHER);
        }

        if(reliableBS){
            System.out.printf("BisectionMethod is reliable for values %.2f, %.2f\n", LOWER, HIGHER);
            Double root_f2 = solverBS.solve(LOWER, HIGHER);
            System.out.println("root solverBS at " + root_f2);
            solverBS.convergenceTime(LOWER, HIGHER);

        }
        else {
            System.out.printf("BisectionMethod is NOT reliable for values %.2f, %.2f \n", LOWER, HIGHER);
        }

    }
}
