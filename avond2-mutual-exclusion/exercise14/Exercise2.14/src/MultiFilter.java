
/**
 * This class is a dumb copy of the Filter class, with some modifications
 * to turn it into an ℓ-exclusion algorithm. The differences are explained
 * in the comments.
 */
public class MultiFilter extends Lock {
	final int N;
	final int ℓ;
	
	volatile int[] level;
	volatile int[] victim;
	
	// Added ℓ parameter. Pretty straight-forward, right?
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
		// The Filter lock allows 1 simultaneous threads in the critical section.
		// This algorithm should allow ℓ, so we need ℓ-1 less waiting rooms.
		// Therefore, we can bound on N-(ℓ-1) = N-ℓ+1.
		for (int i = 1; i < N-ℓ+1; i++) {
			level[me] = i;
			sleep();
			victim[i] = me;
			sleep();
			while (exist(me, i) && victim[i] == me) {}
		}
	}
	
	private boolean exist(int me, int i) {
		// x is the number of other threads which have an equal or higher level than "me",
		// where level[me] = i.
		int x = 0;
		for (int k = 0; k < N; k++) {
			if (k != me && level[k] >= i) {
				// Search ℓ other thread that have equal or higher level.
				x++;
				if (x == ℓ) {
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
