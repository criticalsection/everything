
public class Main {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Hello worlds");
		
		int N = 10;
		Lock lock = new Filter(N);
		Thread[] threads = new Thread[N];
		
		for (int i = 0; i < N; i++) {
			Task t = new Task(lock, i);
			threads[i] = new Thread(t);
			threads[i].start();
		}
		
		System.out.println("All threads were started. Now we wait for them to finish");
		
		for (int i = 0; i < N; i++) {
			threads[i].join();
		}
		
		System.out.println("Done! Bye bye.");
	}
}
