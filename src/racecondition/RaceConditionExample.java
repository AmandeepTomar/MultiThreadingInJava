package racecondition;

public class RaceConditionExample {

    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAmount(100);

        Thread user1 = new Thread(bankAccount);
        Thread user2 = new Thread(bankAccount);
        user1.setName("User1");
        user1.setName("User2");

        user1.start();
        user2.start();

        user1.join();
        user2.join();
        System.out.println(bankAccount.getBalance());
    }

}


class BankAccount implements Runnable{

    private long balance;


    public long getBalance(){
        return balance;
    }

    public void setAmount(long amount){
        this.balance=amount;
    }
    private void withdrawalAmount(long amount){
        if (this.balance>=amount){
            System.out.println(Thread.currentThread().getName()+"  is about to withdrawal");
            balance -= amount;
            System.out.println(Thread.currentThread().getName()+"  has withdrawal "+amount);

        }else {
            System.out.println("Do not have sufficient balance");
        }
    }


    private void synchronizedWithdrawalAmount(long amount){
        synchronized (this){
            if (this.balance>=amount){
                System.out.println(Thread.currentThread().getName()+"  synchronized is about to withdrawal");
                balance -= amount;
                System.out.println(Thread.currentThread().getName()+"  synchronized has withdrawal "+amount);

            }else {
                System.out.println("Do not have sufficient balance");
            }
        }
    }

    @java.lang.Override
    public void run() {
       // withdrawalAmount(75);
        synchronizedWithdrawalAmount(75);
        if (balance<0){
            System.out.println("Money Overdrawn");
        }
    }
}
