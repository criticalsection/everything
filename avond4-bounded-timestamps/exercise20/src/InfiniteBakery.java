public class InfiniteBakery extends Lock {
	private final int N;
	private final TimestampSystem system;
	
	private boolean[] flag;
	
	public InfiniteBakery(int N) {
		this.N = N;
		this.flag = new boolean[N];
		this.system = new TimestampSystem(N);
	}
	
	@Override
	public void lock(int threadId) {
		flag[threadId] = true;
		
		setNewLabelFor(threadId);
		
		waitWhileDominating(threadId);
	}
	
	private void setNewLabelFor(int threadId) {
		// TODO: Find a new label according to the algorithm described on pp35-36,
		//       and assign it to threadId.
		//       This is the hard part! Yay!
	}

	// Wait while there are still threads with their flag raised and that "I" dominate
	private void waitWhileDominating(int threadId) {
		boolean waiting = true;
		while (waiting) {
			Timestamp[] label = system.scan();
			waiting = false;
			for (int i = 0; i < N; i++) {
				if (flag[i] && label[threadId].dominates(label[i])) {
					waiting = true;
				}
			}
		}
	}

	@Override
	public void unlock(int threadId) {
		flag[threadId] = false;
	}
}

class Timestamp {
	private final int[] number;
	
	public Timestamp(int[] number) {
		this.number = number;
	}
	
	public boolean dominates(Timestamp other) {
		for (int i = 0; i < number.length; i++) {
			int diff = this.number[i] - other.number[i];
			
			if (diff == 1 || diff == -2) {
				return true;
			}
			else if (diff == -1 || diff == 2) {
				return false;
			}
			else {
				// fall through
			}
		}
		return false;
	}
}

class TimestampSystem {
	private final Timestamp[] timestamps;
	
	public TimestampSystem(int num) {
		timestamps = new Timestamp[num];
		for (int i = 0; i < num; i++) {
			timestamps[i] = new Timestamp(new int[num-1]);
		}
	}
	
	public synchronized Timestamp[] scan() {
		return timestamps;
	}
	
	public synchronized void label(Timestamp timestamp, int i) {
		timestamps[i] = timestamp;
	}
}
