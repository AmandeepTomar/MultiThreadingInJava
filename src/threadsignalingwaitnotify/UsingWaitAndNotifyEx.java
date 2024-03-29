package threadsignalingwaitnotify;


class CounterX {
    private int x = 0;

    CounterX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void increment() {
        int y = getX();
        y++;
        notifyAll();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sey y" + y);
        setX(y);

    }
}

class Task2 implements Runnable {
    private CounterX counterX;

    Task2(CounterX counterX) {
        this.counterX = counterX;
    }

    @Override
    public void run() {
        synchronized (counterX) {
            while (counterX.getX() < 10) {
                try {
                    counterX.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            counterX.increment();

        }
    }
}

class Task1 implements Runnable {
    private final CounterX counterX;

    Task1(CounterX counterX) {
        this.counterX = counterX;
    }

    @Override
    public void run() {
        synchronized (counterX) {
            while (counterX.getX() < 10) {
                try {
                    counterX.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            counterX.increment();
        }
    }
}

public class UsingWaitAndNotifyEx {

    public static void main(String[] args) {
        CounterX counterX = new CounterX(10);
        Thread t1 = new Thread(new Task1(counterX));
        Thread t2 = new Thread(new Task2(counterX));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counterX.getX());
    }


}
