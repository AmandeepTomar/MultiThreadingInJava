public class SerialProcessingExample {
    public static void main(String[] args) {
        Task1 task1=new Task1();
        Task2 task2=new Task2();

        // execute task1
        task1.printChar10000('A');  // execute first
        System.out.println(task1()); // second execute this then
        task2.printWords10000("JaVa "); // third
        System.out.println(task2()); // fourth execute this


    }


    private static String task1(){
        return "Hello in the world of";
    }

    private static String task2(){
        return "Multi-threading";
    }
}

class Task1{

    public void printChar10000(char ch){
        for (int i = 0; i <10000 ; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }
}

class Task2{
    public void printWords10000(String words){
        for (int i = 0; i <10000 ; i++) {
            System.out.print(words);
        }
        System.out.println();
    }
}
