public class ParallelProcessing {

    public static void main(String[] args) {
        Task1PP task1PP=new Task1PP();
        Task2PP task2PP=new Task2PP();

        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                task1PP.startTask(); // S
            }
        });

        t1.start();

        task2PP.startTask(); // M

//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }
}

class Task1PP{

    public void startTask(){
        for (int i = 0; i <10000 ; i++) {
            System.out.print("S");
        }
    }
}

class Task2PP{

    public void startTask(){
        for (int i = 0; i <10000 ; i++) {
            System.out.print("M");
        }
    }
}
