package threaddesign.copyFilesData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingExecutorService {

    public void executeTaskUsingExecutorService(){
        String srcFile1 = "files/a.txt";
        String sourceFile2 = "files/b.txt";

        String destFile1 = "files/identicaltoa.txt";
        String destFile2 = "files/identicaltob.txt";

        ExecutorService service= Executors.newFixedThreadPool(5);
        service.execute(new MyCopyFilesThread(srcFile1,destFile1));
        service.execute(new MyCopyFilesThread(srcFile1,destFile1));
    }
}
