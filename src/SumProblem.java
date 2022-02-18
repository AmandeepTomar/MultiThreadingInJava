import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class SumCalculator implements Callable<Integer> {
    private int arr[];
    private int start;
    private int end;

    SumCalculator(int arr[],int start,int end){
        this.arr=arr;
        this.start=start;
        this.end=end;
    }

    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (int i = start; i <=end ; i++) {
            sum +=arr[i];
        }
        return sum;
    }
}

public class SumProblem {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int blockSize=3;
        // Number of blocks
        // If say the value for noOfBlocks is 3.3,
        // we know that there are 4 blocks.
        // Hence took the ceil value i.e. 4.
        int noOfBlock=(int) Math.ceil((double) arr.length/blockSize);

        ExecutorService service= Executors.newFixedThreadPool(3);

        List<Future<Integer>> list=new ArrayList<>();

        int start=0,end;
        for (int i = 1; i <=noOfBlock ; i++) {
// Calculate the starting and ending index positions
            // of the i th partition.
            start = (i-1) * blockSize;
            end = start + blockSize - 1;

            // Check if end crosses the actual array size,
            // if yes take the last
            // element index.
            if (end >= arr.length) {
                end = arr.length - 1;
            }
            Future<Integer> future=service.submit(new SumCalculator(arr,start,end));
            list.add(future);
        }

       int total=0;
        for(Future<Integer> future : list){
            total +=getValueFromFuture(future);
            System.out.println("Sub total is "+total);
        }

        System.out.println("Final total is "+total);

    }

    private static int getValueFromFuture(Future<Integer> future) throws ExecutionException, InterruptedException {
        while (!future.isDone()){
            Thread.yield();
        }
        try {
            return future.get();
        }catch (InterruptedException e){
            System.out.println(e);
            return 0;
        }
    }


}
