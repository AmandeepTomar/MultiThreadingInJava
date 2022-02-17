package daemonthreadex;

class MyTask implements Runnable{
    @Override
    public void run() {
        for (;;) {
            System.out.print("I");
        }// infiniteloop
    }
}

public class DaemonThreadEx {
    public static void main(String[] args) {
        Thread thread =new Thread(new MyTask(),"DaemonThreadMy");
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i <500 ; i++) {
            System.out.print(i);
        }
    }
}
