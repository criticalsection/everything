package exercise15;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author rix0rrr
 */
public class Exercise15 {
    private FastPath fast = new FastPath(new ReentrantLock());

    public static void main(String[] args) throws InterruptedException {
        new Exercise15().run();
    }
        
    private void run() throws InterruptedException {
        int N = 2;
        int runTimeSecs = 10;
        
        for (int i = 0; i < N; i++) {
            Thread t = new Thread(new Task(i));
            t.setDaemon(true);
            t.start();
        }
        
        Thread.sleep(runTimeSecs * 1000);
    }
    
    private AtomicInteger checker = new AtomicInteger(-1);
            
    private class Task implements Runnable {
        private final int i;
        
        public Task(int i) {
            this.i = i;
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    fast.lock(i);
                    
                    if (!checker.compareAndSet(-1, i))
                        System.err.println("FAIL");

                    Thread.sleep(0);

                    if (!checker.compareAndSet(i, -1))
                        System.err.println("FAIL");
                    
                    fast.unlock(i);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
