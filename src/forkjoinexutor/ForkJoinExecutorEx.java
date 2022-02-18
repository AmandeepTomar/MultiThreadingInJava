package forkjoinexutor;

import finderproblem.FinderUtility;

import java.util.concurrent.RecursiveTask;

class Fibonacci  extends RecursiveTask<Integer>{
    private int num;
    Fibonacci(int num){
        this.num=num;
    }
    @Override
    protected Integer compute() {
        if (num<1){
            return num;
        }
        Fibonacci fibonacci=new Fibonacci(num-1);
        fibonacci.fork();//sub task
        Fibonacci fibonacci2=new Fibonacci(num-2);
        fibonacci2.fork();
        return fibonacci.join() + fibonacci2.join();
    }
}

public class ForkJoinExecutorEx {

    public static void main(String[] args) {
        Fibonacci f=new Fibonacci(3);
        System.out.println(f.compute());

    }
}
