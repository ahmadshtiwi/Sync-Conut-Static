
import java.util.concurrent.CountDownLatch;

public class SimpleCounterStatic {

    private static int counter;
    public  void inc(){
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {

        SimpleCounterStatic simpleCounterStatic1 = new SimpleCounterStatic();
        SimpleCounterStatic simpleCounterStatic2 = new SimpleCounterStatic();
        int numberOfThreads = 10;
        int numberOfIteration = 10000;
        CountDownLatch downLatch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(()-> {
                for (int i1 = 0; i1 < numberOfIteration; i1++) {



                    synchronized (simpleCounterStatic1) {
                        simpleCounterStatic1.inc();
                        simpleCounterStatic2.inc();

                    }
                }
                downLatch.countDown();
            }).start();
        }

        downLatch.await();
        System.out.println("simpleCounter.counter = " + SimpleCounterStatic.counter);
    }

}
