package concurrencySolutions;

class Counter{
    private int x;

   public int getX(){
       return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    // synchronized key word is the solution for the same.
    public synchronized void increment() throws InterruptedException {
       int y=getX();
       y++;
       Thread.sleep(100);
       setX(y);
    }
}

class  MyThread implements Runnable{
    private Counter counter;
    MyThread(Counter counter){
        this.counter=counter;
    }
    @Override
    public void run() {
        try {
            counter.increment();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrencyProblem {
    public static void main(String[] args) {
        Counter counter=new Counter();
        counter.setX(100);

        Thread t1=new Thread(new MyThread(counter));
        Thread t2=new Thread(new MyThread(counter));
        Thread t3=new Thread(new MyThread(counter));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Counter values should be 103 ="+counter.getX());
    }
}
