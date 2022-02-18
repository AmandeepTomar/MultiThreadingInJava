package reentrantlock;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyThread implements Runnable {

    private Counter counter;

    MyThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}

class Counter {
    private int x;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    Counter(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void increment() {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        int y = getX();
        y++;
        try {
            Thread.sleep(100);

            setX(y);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // here if  due to any reason we are not able to release the lock then release here.
            lock.unlock();
        }
    }
}

public class ReentrantLockEx {
    public static void main(String[] args) {
        Counter counter = new Counter(10);
        Thread t1 = new Thread(new MyThread(counter));
        Thread t2 = new Thread(new MyThread(counter));
        Thread t3 = new Thread(new MyThread(counter));
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Counter is " + counter.getX());
    }


}
