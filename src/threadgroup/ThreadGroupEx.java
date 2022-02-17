package threadgroup;

class MyTask implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadGroupEx {

    public static void main(String[] args) {
        ThreadGroup threadGroup=new ThreadGroup("DemoThreadGroup");
        threadGroup.setMaxPriority(4);

        Thread thread=new Thread(threadGroup,new MyTask(),"MYTaskThread");
        Thread thread1=new Thread(threadGroup,new MyTask(),"MYTaskThread1");
        Thread thread2=new Thread(threadGroup,new MyTask(),"MYTaskThread2");
        Thread thread3=new Thread(threadGroup,new MyTask(),"MYTaskThread3");
        thread1.start();
        thread2.start();
        thread3.start();
        thread.start();

       // threadGroup.interrupt();

        mainThreadInfo();
    }

    private static void mainThreadInfo(){
        Thread main=Thread.currentThread();
        ThreadGroup threadGroup=main.getThreadGroup();
        while (threadGroup.getParent()!=null){
            threadGroup=threadGroup.getParent();
        }
        // Prints information about this thread group to the standard output. This method is useful only for debugging
        threadGroup.list();
    }
}
