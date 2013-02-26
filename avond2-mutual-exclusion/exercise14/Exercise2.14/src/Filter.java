
public class Filter implements Lock {
	final int N;
	volatile int[] level;
	volatile int[] victim;
	
	public Filter(int n) {
		N = n;
		level = new int[n];
		victim = new int[n];
		for (int i = 0; i < n; i++) {
			level[i] = 0;
		}
	}
	
	@Override
	public void lock(int threadId) {
		int me = threadId;
		for (int i = 1; i < N; i++) {
			level[me] = i;
			victim[i] = me;
			while (exist(me, i) && victim[i] == me) {}
		}
	}
	
	private boolean exist(int me, int i) {
		for (int k = 0; k < N; k++) {
			if (k != me && level[k] >= i) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void unlock(int threadId) {
		int me = threadId;
		level[me] = 0;
	}
}
