package concurrencySolutions;

class StaticCounter{
    static int a=0;
    private int b;
    StaticCounter(int b){
        this.b=b;
    }

    public int getB() {
        return b;
    }
    /**
     * here problem with the a because its not a object property , its is static so it is class level.
     * When we use multiple object with multiple  thread then we need lock on one object so synchronized keyword
     * lock the object but bot class level like a. b is fine with this approch.
     * */
    public synchronized void increment(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b++;

        a++;
    }

    /**
     * here we aquire lock on object and on class level too, using synchronized block for class and object both.
     * */
    public  void incrementSolForStatic(){
        synchronized (this) {
            b++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (StaticCounter.this) {
            a++;
        }
    }

    /**
     * This approch is not file use the block.
     * */
    public  void incrementSolForStatic2(){
        synchronized (this) {
            b++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        incrementA();
    }

    private static synchronized void incrementA(){
        a++;
    }
}

public class ProblemWithStatic {

    public static void main(String[] args) {


        provideWrongValue();
//        provideSolutionWithSynchronizedBlock();
//        provideSolutionForStaticWithAnotherApproach();
    }




    private static void provideSolutionForStaticWithAnotherApproach() {
        StaticCounter counter=new StaticCounter(0);

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementSolForStatic2();
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementSolForStatic2();
            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementSolForStatic2();
            }
        });

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
        //  a==b=3
        System.out.println(" provideSolutionForStaticWithAnotherApproach() : Value of a = "+StaticCounter.a+" Value of b ="+counter.getB());

    }

    private static void provideSolutionWithSynchronizedBlock() {
        StaticCounter counter=new StaticCounter(0);


        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementSolForStatic();
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementSolForStatic();
            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.incrementSolForStatic();
            }
        });

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
        //  a==b=3
        System.out.println(" provideSolutionWithSynchronizedBlock() : Value of a = "+StaticCounter.a+" Value of b ="+counter.getB());

    }

    private static void provideWrongValue() {
        StaticCounter counter=new StaticCounter(0);

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        Thread t4=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        Thread t5=new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
         //  a==b=3
        System.out.println(" provideWrongValue() : Value of a = "+StaticCounter.a+" Value of b ="+counter.getB());

    }
}
