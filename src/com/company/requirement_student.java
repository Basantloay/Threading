package com.company;

class Threadsrequirement {
    public static void main(String... args) throws InterruptedException {
        BookStock b = new BookStock (10);
        Thread sup=new Thread(new Supplier(b));
        Thread t1=new Thread(new StoreBranch(b));
        Thread t2=new Thread(new StoreBranch(b));
        Thread t3=new Thread(new StoreBranch(b));
        sup.setName("Supplier");
        t1.setName("Giza Branch");
        t2.setName("Cairo Branch");
        t3.setName("Alex Branch");
        /*
         * TODO 1: Create 4 threads,
         * 1 thread for Supplier, named "Supplier".
         * 3 threads for StoreBranches and name them: "Giza Branch", "Cairo Branch", and "Alex Branch".
         */

        /*
         * TODO-2: Run the 4 threads.
         *
         */
        sup.start();t1.start();t2.start();t3.start();
        //sup.join();t1.join(); t2.join();t3.join();
    }
}

class BookStock {
    private int bookCount;
    private final int maxCount;
    public BookStock  (int max) {
        this.maxCount = max;
    }
    public void produce() {
        bookCount++;
    }

    public void consume () {
        bookCount--;
    }

    public int getCount () {
        return bookCount;
    }

    public final int getMaxCount() {
        return maxCount;
    }
}

/*
 * TODO-3: Should the class Supplier extend any class or implement any interface?
 */
class Supplier implements Runnable {
    private BookStock b;

    public Supplier (BookStock b) {
        this.b = b;
    }

    /*
     * TODO-4: Is there a function missing here? What does this function do?
     */
    @Override
    public void run(){
        doWork();
    }

    public void doWork () {

        while (true) {synchronized (this) {
            /*
             * TODO-5: How to make the supplier stop producing when it reaches maxCount,
             *  without adding extra sleeps or busy waiting ?
             *  Check Example 11 in the lab code examples.
             */

                    if (b.getCount() < b.getMaxCount()) {
                        b.produce();
                        System.out.println(Thread.currentThread().getName() + " provided a book, total " + b.getCount());
                        notifyAll();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "is awaken");
                    }
                }
            }
        }


}

/*
 * TODO-6: Should the class StoreBranch extend any class or implement any interface?
 */
class StoreBranch implements Runnable {
    private BookStock b;

    public StoreBranch(BookStock b) {
        this.b = b;
    }

    /*
     * TODO-7: Is there a function missing here? What does this function do?
     */
    @Override
    public void run() {
        doWork();
    }

    public void doWork() {

        while (true) {

            synchronized (this.b) {
            /*
             * TODO-8: How to make the store branch stop consuming when the store is empty,
             *  without adding extra sleeps or busy waiting ?
             *  Check Example 11 in lab code examples.
             */

                if (b.getCount() > 0) {
                    b.consume();
                    System.out.println(Thread.currentThread().getName() + " sold a book");

                }
                else
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "is awaken");
                    }
                }
            }
        }
    }
