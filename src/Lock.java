
public class Lock {

	private Account acc;
	// Which transactions are holding the lock
	private int[] holder_ids; 
	private String lock_type;
	private boolean exist_conflict; 
	
	public synchronized void acquire(int trans_id, String lock_type)
	{
		
	}

	private void check_conflicts()
	{
		// Check if anyone else holds the same lock.
		// If so, check it's type. Read-read ok, but anything with write will cause conflict. 
		// If there is a conflict set that conflicts exist to true 
	}
}
