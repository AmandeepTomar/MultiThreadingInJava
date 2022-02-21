# MultiThreadingInJava

### Serial processing 
- When you want to execute the task in serial execution you write the code in order in which you want to execute.
- [SerialProcessingExample]: https://github.com/AmandeepTomar/MultiThreadingInJava/blob/master/src/SerialProcessingExample.java "GoToFile"
- Multi-threaded applications normally do parallel processing of tasks where as single threaded applications do one task at a time i.e. If there are two tasks such as T1, T2 they are executed in serial order i.e T1 after T2 or T2 after T1, where as multi threading enables us to execute them simultaneously i.e. T1 along with T2. We can choose single threaded applications when parallel processing is not required example use cases such as simple text editor.

### parallel Processing 
- Its like we execute two task parallel. like if we one CPU need to achieve the parallel execution then switching the Task T1 and T2 in some intervals.

## Thread 
- Create thread using `Thread` class and `Runnable` interface 
- Best one is using the `Runnable` interface because we are just using the `run()`. then why should we extends `Thread` class and not inherit any property.
### Implementation
- Using `Thread` class
```java
    class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.print("T");
            }
        }
    }

    // Implementation 
    MyThread thread=new MyThread();
        thread.start();
```
- Using `Runnable` Interface
```java
    class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.print("R");
    }
    // Implementation
    MyThread2 thread2=new MyThread2(); // task
    Thread newThread=new Thread(thread2);
        newThread.start();
}
```

### Stopping thread in middle 
- `Thread.sleep(milisec)` block the thread for given time of interval. it throws interruptedException
- Why thread.stop() is dangerous ? 
  - You are not giving an opportunity to the thread to rollback or reverse whatever the action it performed.
- What shoud we do?
  - We should use the `interrupt()` the thread. now you tell the thread to stop now it will be decided by thread to stop itself opr not.
  - we can read the signal using `interrupted()` and perform same task here.

### Thread State
- A thread executing in the Java virtual machine is in this state, internally we can think of it as a combination of two sub states Ready and Running, i.e. when you start the thread it comes to Ready state and wait for the CPU, and if CPU is allocated then it goes into Running state. Once allocated CPU time is completed, in other words when the Thread schedular suspends the thread then it goes back to the Ready state and waits for its turn again.

The yield() method instructs the thread schedular to pass the CPU to other waiting thread if any.

#### BLOCKED

- A thread is blocked if it is waiting for a monitor lock is in this state. Refer synchronized methods and blocks.

#### WAITING

- A thread that is waiting indefinitely for another thread to perform a particular action is in this state. Refer `wait(), join()`

#### TIMED_WAITING

A thread that is waiting for another thread to perform an action for up to a specified waiting time is in this state. Refer `wait(millis), join(millis)`

#### TERMINATED

A thread that has exited i.e. it has either completed its task or terminated in the middle of the execution.


`yield() method` -

- yield() method is important in few scenarios, suppose a thread is given 5 min of CPU time, now after a minute thread knows that it doesn't need the CPU anymore with in that time period, in such scenarios do you think that blocking the CPU for the next four minutes is a good idea ? No, it is better to pass on the control to the threads if any waiting for CPU and that is when we can use the yield() method. Usage Thread.yield(), it is a static method of the Thread class and it affects the current thread from which the method is invoked.

### Thread Priority 
- MIN-1
- DEFAULT 5 
- HIGHEST 10
- `newThread.setPriority(8);`

#### Internal system thread and Thread group 
- We can create a Thread group and add thread in to groups 
- Example 
````java
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

    outPut
            java.lang.ThreadGroup[name=DemoThreadGroup,maxpri=4]
            Thread[MYTaskThread1,4,DemoThreadGroup]
            Thread[MYTaskThread2,4,DemoThreadGroup]
            Thread[MYTaskThread3,4,DemoThreadGroup]
            Thread[MYTaskThread,4,DemoThreadGroup] 
````
- Basically we use thread group whenwe wnat to group the related thread into one group so that we can set the same priority to all threads or if  we interrupt thread group all thread associated with that group will be interrupted.


### Daemon Thread
- Daemon threads are the ones which does not prevent the JVM from exiting the application once it finishes. Daemon threads are handy for performing background tasks such as garbage collection or collecting application statistics etc. Note that the Java Virtual Machine exits when the only threads running are all daemon threads.
- Thread will terminate one the main thread end. 
- Garbage collection and some statics 
- Reference Handler , Finalizer and Signal Dispatcher all is `Daemon Thread.`
- Main thread is not Daemon.

### Callable Interface
- this one is used when we want to return something from the thread.
- Unlike Runnable, Callable interface allows us to create an asynchronous task which is capable of returning an Object.
```java
interface Callable<E> {
    E call() throws Exception;
}
```
- <B>Future Object -</B>
  - When you submit a Callable task to the ExecutorService, it returns a Future object. This object enables us to access the request and check for the result of the operation if it is completed.
  
- <B>Important methods -</B>

- `isDone()` - Returns true if the task is done and false otherwise.

- `get()` - Returns the result if the task is done, otherwise waits till the task is done and then it returns the result.

- `cancel(boolean mayInterrupt)` - Used to stop the task, stops it immediately if not started, otherwise interrupts the task only if mayInterrupt is true.

## Concurrency 
- `synchronized` this make the method thread safe.
```java
    static int a=0;
    private int b;
// This method is not thread safe because synchronized lock b because it is object while a is static so its not locked that so problem with a.
   public synchronized void increment(){
          b++;
          a++;
      }
  }
  
  // solution
```
## Deadlock and solution 
- lock should be used in the same manned both side ,
- like if pen locked first then need to lock pen first in other Writer too.
- here first write lock the book and the Thread go to sleep context changes and now writer2
- lock the pen so deadlock situation is arrived, to fix this we need to provide the resource in same manner.
- for both object book first then pen.
```java
// Code from Thread2
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


// Code from thread2
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

```

## Reentrant lock
- Read/Write lock 
- readLock allows us to lock the object for read operation, and the interesting point is that the read operation can be shared i.e if two threads are waiting for readLock then both of them can proceed forward with the operation as read operation doesn't change the data.
- Where as writeLock is mutually exclusive i.e. if a writeLock is accepted then all the other lock requests should wait till the thread that owns the lock releases it.
 
- For example let us assume the following chronologically ordered lock requests

T1 -> lock.readLock();

T2 -> lock.readLock();

T3 -> lock.readLock();

T4 -> lock.writeLock();

T5 -> lock.readLock();

Here T1, T2, T3 can share the readLock and proceed forward with the operation.  Where T4 should wait till T1, T2 and T3 unlocks.

Why T5 is waiting ?

Because writeLock is requested by T4 before its request and hence all subsequent requests to read/write locks should wait.

This is in contrast to synchronized methods/blocks because for synchronized method/block there is no segregation of read and write operations. Object is locked no matter whether it is read or write.

Caution - It is always better to put the unlock operation in finally, as you need to unlock irrespective of exceptions.

### Thread Signalling 

- Thread signalling using wait() and notify() -
- wait() method Releases the lock over the object and takes the thread to WAITING state. And the thread remains in that state until some other thread calls the notify() method over the same object. Once notify() is invoked it ends the wait for one single thread and takes the thread to BLOCKED state where the thread remains in that state till the lock is obtained. wait() only returns after obtaining the lock.
- wait(long millis)  slightly differs, as it takes thread to TIMED_WAITING and waits only for the specified duration.

notify() - notifies one single thread where as notifyAll() notifies all the threads waiting for the signal.


### References 
- https://udemy.com/course/java-multi-threading-by-sagar