import java.util.Random;

public abstract class Lock {
	public abstract void lock(int threadId);
	public abstract void unlock(int threadId);
	
	public void sleep() {
		try {
			int ms = new Random().nextInt(20);
			Thread.sleep(ms);
		}
		catch (InterruptedException ignored) {}
	}
}
