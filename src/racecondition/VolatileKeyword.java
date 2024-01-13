package racecondition;

import java.util.concurrent.TimeUnit;

public class VolatileKeyword {
        private static volatile boolean isStop;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!isStop){
                System.out.println("Inside thread....");
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        isStop = true;
    }
}


 class MemoryShareBySynchronized{
    private static boolean stop=false;

    private void requestToStop(){
        synchronized (MemoryShareBySynchronized.this){
            stop = true;
        }
    }

    private synchronized static boolean getStop(){
        return stop;
    }
    public static void main(String[] args) throws InterruptedException {
            MemoryShareBySynchronized obj = new MemoryShareBySynchronized();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!getStop()){
                        System.out.println("Printing inside thread !!!!");
                    }
                }
            }).start();

            TimeUnit.SECONDS.sleep(1);

            obj.requestToStop();
    }
}
