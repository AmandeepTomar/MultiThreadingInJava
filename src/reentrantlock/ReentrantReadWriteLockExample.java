package reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockExample {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int data = 0; // Some shared data

    public int readData() {
        lock.readLock().lock(); // Acquire read lock
        try {
            // Introducing a small delay to simulate some processing time
            Thread.sleep(10);
            // Reading data here
            return data;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return -1; // Return -1 in case of interruption
        } finally {
            lock.readLock().unlock(); // Release read lock
        }
    }

    public void writeData(int newValue) {
        lock.writeLock().lock(); // Acquire write lock
        try {
            // Introducing a small delay to simulate some processing time

            // Writing data here
            data = newValue;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.writeLock().unlock(); // Release write lock
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockExample example = new ReentrantReadWriteLockExample();

        // Start multiple reader threads
        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Reading data: " + example.readData());
            }).start();
        }

        // Pause for a short time to allow some readers to start before writing
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Start a writer thread
        new Thread(() -> {
            example.writeData(10); // Setting data to 10
            System.out.println("Data has been written.");
        }).start();
    }
}
