package deadlockandsolution;

class Book{

}

class Pen{

}

class Write1 extends  Thread{
    private Book book;
    private Pen pen;

    Write1(Book book,Pen pen){
        this.book=book;
        this.pen=pen;
    }

    @Override
    public void run() {
        synchronized (book){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (pen){
                System.out.println("Writer 1 writing book");
            }
        }
    }
}

class Write2 extends  Thread{
    private Book book;
    private Pen pen;

    Write2(Book book,Pen pen){
        this.book=book;
        this.pen=pen;
    }

    @Override
    public void run() {
        // lock should be used in the same manned both side ,
        // like if pen locked first then need to lock pen first in other Writer too.
        // here first write lock the book and the Thread go to sleep context changes and now writer2
        // lock the pen so deadlock situation is arrived, to fix this we need to provide the resource in same manner.
        // for both object book first then pen.
        // DealLock code.
//        synchronized (pen){
//            synchronized (book){
//                System.out.println("Writer 2 writing book");
//            }
//        }
    // Solved deal lock by providing lock in same manner
        synchronized (book){
            synchronized (pen){
                System.out.println("Writer 2 writing book");
            }
        }
    }
}


public class BookPenProblem {

    public static void main(String[] args) {
        Book book=new Book();
        Pen pen=new Pen();
        new Write1(book,pen).start();
        new Write2(book,pen).start();
        System.out.println("Main Complete");
    }
}
