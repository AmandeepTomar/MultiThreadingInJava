package finderproblem;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class FinderProblemSolution {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        SerialSolution serialSolution=new SerialSolution();
        ParallelSolution parallelSolution=new ParallelSolution();
        long time=System.currentTimeMillis();
        serialSolution.findPattersOnLineNo();
        System.out.println("Time taken by serial "+(System.currentTimeMillis()-time));

        long newTime=System.currentTimeMillis();
        parallelSolution.findPatternInParallel();
        System.out.println("Time taken in parallel "+(System.currentTimeMillis()-newTime));
    }
}
