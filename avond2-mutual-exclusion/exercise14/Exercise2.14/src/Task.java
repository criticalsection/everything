import java.util.Random;


public class Task implements Runnable {
	private final Lock lock;
	private final int id;
	
	public Task(Lock lock, int id) {
		this.lock = lock;
		this.id = id;
	}
	
	@Override
	public void run() {
		log("Entering run");
		try {
			log("Locking");
			lock.lock(id);
			int sleep = new Random().nextInt(1000);
			log("Entered CRITICAL SECTION!! Sleeping " + String.format("%4d", sleep) + "ms        ---->");
			Thread.sleep(sleep);
		}
		catch (InterruptedException ignored) {
		}
		finally {
			log("Unlocking                                                        <----");
			lock.unlock(id);
		}
		log("Exiting run");
	}
	
	private void log(String s) {
		System.out.println("Thread " + String.format("%3d", id) + " " + s);
	}
}
