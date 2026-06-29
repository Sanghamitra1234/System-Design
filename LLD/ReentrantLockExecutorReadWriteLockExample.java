import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockExecutorReadWriteLockExample {
  private String log = "Initial log entry.";

  // Create a ReentrantReadWriteLock instance.
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

   // Simulate processing work using a dummy computation loop. 
    private void simulateWork() { 
        long sum = 0; 
        for (int i = 0; i < 500000; i++) { 
            sum += i; 
        } 
        // (The computed sum is discarded; its purpose is solely to consume CPU time.) 
    } 

  public void readLog(String taskName) {
    lock.readLock().lock();;
    try {
      System.out.println(taskName + " Reading the value of log: " + log);
      simulateWork(); // Simulate some work while holding the read lock.
    } finally {
      System.out.println(taskName + " released the read lock.");
      lock.readLock().unlock();
    }
  }

  public void writeLog(String taskName, String newLog) {
    lock.writeLock().lock();
    try {
      System.out.println(taskName + " Writing the value of log: " + newLog);
      log = newLog;
      simulateWork(); // Simulate some work while holding the write lock.
    } finally {
      System.out.println(taskName + " released the write lock.");
      lock.writeLock().unlock();
    }
  }


  public static void main(String[] args) {
    ReentrantLockExecutorReadWriteLockExample logExample = new ReentrantLockExecutorReadWriteLockExample();
    // Create an ExecutorService with a fixed thread pool of 5 threads.
    ExecutorService executor = Executors.newFixedThreadPool(2);

    // Submit three concurrent reader tasks. 
        executor.submit(() -> logExample.readLog("Reader-2")); 
        executor.submit(() -> logExample.readLog("Reader-3")); 

        // Submit a writer task. 
        executor.submit(() -> logExample.writeLog("Writer-1", "New log entry"));
        executor.submit(() -> logExample.writeLog("Writer-2", "New log entry-2"));

        executor.submit(() -> logExample.readLog("Reader-2"));
        executor.submit(() -> logExample.readLog("Reader-5"));  


        executor.submit(() -> logExample.writeLog("Writer-2", "New log entry-6"));
        executor.submit(() -> logExample.readLog("Reader-5"));  

        executor.shutdown(); 
      }

}