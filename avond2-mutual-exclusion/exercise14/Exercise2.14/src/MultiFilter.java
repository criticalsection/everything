
public class MultiFilter extends Lock {
	final int N;
	final int ℓ;
	
	volatile int[] level;
	volatile int[] victim;
	
	public MultiFilter(int N, int ℓ) {
		this.N = N;
		this.ℓ = ℓ;
		level = new int[N];
		victim = new int[N];
		for (int i = 0; i < N; i++) {
			level[i] = 0;
		}
	}
	
	@Override
	public void lock(int threadId) {
		int me = threadId;
		for (int i = 1; i < N-ℓ+1; i++) {
			level[me] = i;
			sleep();
			victim[i] = me;
			sleep();
			while (exist(me, i) && victim[i] == me) {}
		}
	}
	
	private boolean exist(int me, int i) {
		int num = 0;
		for (int k = 0; k < N; k++) {
			if (k != me && level[k] >= i) {
				num++;
				if (num == ℓ) {
					return true;
				}
			}
			sleep();
		}
		return false;
	}
	
	@Override
	public void unlock(int threadId) {
		int me = threadId;
		level[me] = 0;
	}
}
