package executors;

import java.net.ConnectException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorsExample {

    public static void main(String[] args) {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            executor.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("name " + Thread.currentThread().getName());
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            System.out.println(Thread.currentThread().getName());
            executor.shutdown();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


    }
}
