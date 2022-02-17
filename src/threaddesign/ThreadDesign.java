package threaddesign;


import java.util.concurrent.*;

public class ThreadDesign {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        MyThread thread=new MyThread();
//        thread.start();
//
//
//        MyThread2 thread2=new MyThread2(); // task
//        Thread newThread=new Thread(thread2);
//
//        newThread.start();
//
//        for (int i = 0; i <1000 ; i++) {
//            System.out.print("M");
//        }
//
        MyCallableThread callableThread=new MyCallableThread("My Name is Callable Thread");
        ExecutorService executorServic= Executors.newFixedThreadPool(1);
       Future<Integer> future=executorServic.submit(callableThread);
      int result= future.get();
        System.out.println("Result is"+result);


    }
}

// Implements Runnable
class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.print("R");
    }
}

// By extending Thread
class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.print("T");
        }

    }
}

// By using Callable Interface

class MyCallableThread implements Callable<Integer>{
    private String word;
    MyCallableThread(String word){
        this.word=word;
    }
    @Override
    public Integer call() throws Exception {
        String arr[]=word.split(" ");
        return arr.length;
    }
}
