package producerconsumerproblem;

import java.util.ArrayList;
import java.util.List;

class MessageQueue{
    private List<String> messageQueue;
    int limit;
    MessageQueue(int limit){
        messageQueue=new ArrayList<>();
        this.limit=limit;
    }

    public boolean isFull(){
       return messageQueue.size()==limit;
    }

    public boolean isEmpty(){
        return messageQueue.size()==0;
    }

    public synchronized void enqueue(String value)  {
            while (isFull()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            messageQueue.add(value);
            this.notify();

    }

    public synchronized String dequeue(){

            while (messageQueue.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String message = messageQueue.remove(0);
            this.notify();
            return message;
    }
}

class Producer implements Runnable{
    private MessageQueue messageQueue;
    Producer(MessageQueue messageQueue){
        this.messageQueue=messageQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
            String value=""+i;
            messageQueue.enqueue(value);
            System.out.println("Produced - "+value);

        }
    }
}

class Consumer implements Runnable{
    MessageQueue messageQueue;
    Consumer(MessageQueue messageQueue){
        this.messageQueue=messageQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <=10 ; i++) {
           String dequeue = messageQueue.dequeue();
            System.out.println("Consumed "+dequeue);
        }
    }
}

public class ProducerConsumerProblem {
    public static void main(String[] args) {
        MessageQueue messageQueue=new MessageQueue(5);
        Thread producer=new Thread(new Producer(messageQueue));
        Thread consumer=new Thread(new Consumer(messageQueue));

        producer.start();
        consumer.start();
    }



}
