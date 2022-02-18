package finderproblem;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

public class ParallelSolution {

    public void findPatternInParallel() throws ExecutionException, InterruptedException {
        String pattern="public";
        String path="sample/";

        File file=new File(path);
        File []files=file.listFiles();
        FinderUtility finder=new FinderUtility();
        ExecutorService service= Executors.newFixedThreadPool(5);
        HashMap<String,Object> resultMap=new HashMap<>();


        for (File f:files){
            Future<List<Integer>> future= service.submit(new Callable<List<Integer>>() {
                    @Override
                    public List<Integer> call() throws Exception {
                        return  finder.findPattern(f,pattern);
                    }
                });
            resultMap.put(f.getName(),future);
        }

        waitForAllResult(resultMap);

        for (Map.Entry<String,Object> entry:resultMap.entrySet()){
            System.out.println(pattern + " found at - " + entry.getValue() + " in file " + entry.getKey());
        }
    }

    private void waitForAllResult(HashMap<String, Object> resultMap) throws ExecutionException, InterruptedException {
        // we need to get all from future
        Set<String> keys=resultMap.keySet();
        for (String key:keys){
            Future<List<Integer>>future= (Future<List<Integer>>) resultMap.get(key);
            while (!future.isDone()){
                Thread.yield(); // just swith to ready mode or provide access to some other thread who is waiting
            }

            resultMap.put(key,future.get());
        }
    }
}
