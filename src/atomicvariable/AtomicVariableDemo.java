package atomicvariable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableDemo {

    public static void main(String[] args) throws InterruptedException {
        MeetupSimulator meetupSimulator = new MeetupSimulator("Android Group");

        Thread user1 = new Thread(() -> {
            meetupSimulator.attending(4);
            System.out.println(Thread.currentThread().getName()+"  user 1 added "+meetupSimulator.getCount());
        });

        Thread user2 = new Thread(() -> {
            meetupSimulator.attending(4);
            System.out.println(Thread.currentThread().getName()+"  user 2 added "+meetupSimulator.getCount());
            meetupSimulator.notAttending(3);
            System.out.println(Thread.currentThread().getName()+"  user 2 removed "+meetupSimulator.getCount());
        });
        Thread user3 = new Thread(() -> {
            meetupSimulator.attending(2);
            System.out.println(Thread.currentThread().getName()+"  user 3 added "+meetupSimulator.getCount());
        });


        user1.setName("User1");
        user2.setName("User2");
        user3.setName("User3");


        user1.start();
        TimeUnit.SECONDS.sleep(1);
        user2.start();
        TimeUnit.SECONDS.sleep(2);
        user3.start();
        TimeUnit.SECONDS.sleep(2);

        user2.join();
        System.out.println(Thread.currentThread().getName()+" "+meetupSimulator.getCount());
    }

}


class MeetupSimulator{
    private String name;
    private AtomicInteger count = new AtomicInteger(1);

    public MeetupSimulator(String name) {
        this.name = name;
    }

    public void attending(int guest){
        if (guest==1){
            count.getAndIncrement();
        }else {
            count.getAndAdd(guest);
        }
    }


    public void notAttending(int guest){
        if (guest==1){
            count.decrementAndGet();
        }else {
            boolean update = false;
            while (!update){
                int currentCount = count.get();
                int newCount = currentCount-guest;
                update = count.compareAndSet(currentCount,newCount);
            }
        }
    }


    public int getCount(){
        return count.get();
    }


}
