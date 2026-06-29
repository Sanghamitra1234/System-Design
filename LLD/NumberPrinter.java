package code;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberPrinter  {

    int counter = 0;
    int start, end;

    public synchronized void printNext() {
        if (counter <= 100) {
            System.out.println(counter + " "+ Thread.currentThread().getName());
            counter++;
        }
    }

    public static void main(String [] args) {

        int totalNumbers = 100;
        int numThreads =    10;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        NumberPrinter printer = new NumberPrinter();

        //  executor.submit(() -> {
        //             while (printer.counter <= 100) {
        //             printer.printNext();
        //         }
        //     });
        for (int i = 0; i < 50; i++) {
                executor.submit(() -> {
                    try {
                        System.out.println("Thread " + Thread.currentThread().getName() + " is sleeping");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // while (printer.counter <= 100) {
                    // printer.printNext();
                // }
            });
        }

        executor.shutdown();

        // synchronised
        // NumberPrinter printer = new NumberPrinter();
        // Thread t1 = new Thread(() -> {
        //     while (printer.counter <= 100) {
        //         printer.printNext();
        //     }
        // });

        // Thread t2 = new Thread(() -> {
        //     while (printer.counter <= 100) {
        //         printer.printNext();
        //     }
        // });

        // Thread t3 = new Thread(() -> {
        //     while (printer.counter <= 100) {
        //         printer.printNext();
        //     }
        // });
    //     t1.start();
    //     t2.start();
    //     t3.start();
    }
}
    // public static void main(String[] args) {
    //     int totalNumbers = 1000;
    //     int numThreads = 10;
    //     int numbersPerThread = totalNumbers / numThreads;
        
    //     // Create a thread pool with 10 threads
    //     ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        
    //     // Submit 10 tasks to print numbers
    //     for (int i = 0; i < numThreads; i++) {
    //         int start = i * numbersPerThread + 1;
    //         int end = (i == numThreads - 1) ? totalNumbers : (i + 1) * numbersPerThread;
            
    //         executor.submit(() -> {
    //             String threadName = java.lang.Thread.currentThread().getName();
    //             System.out.println(threadName + " printing from " + start + " to " + end);
    //             for (int num = start; num <= end; num++) {
    //                 System.out.println(num);
    //             }
    //         });
    //     }
        
    //     // Shutdown the executor
    //     executor.shutdown();
    //     System.out.println("All tasks submitted!");
    // }