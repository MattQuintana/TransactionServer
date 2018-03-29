
import java.util.*;

public class LockManager {
	
	// List of locks that are being used
	private List<Lock> lock_list = new ArrayList<Lock>();
	private boolean locking_enabled;
	private int num_locks = 0;
	
	
	public void enableLocking(boolean state)
	{
		locking_enabled = state;
	}
	
	// Lock a transaction
	public void setLock(Transaction trans, int trans_id, String lock_type)
	{
		// If locking is enabled in the first place
		if (locking_enabled)
		{
			Lock found_lock;
			synchronized(this) 
			{
				// If there are no locks associated with a transaction
				if(trans.locks.isEmpty())
				{
					// Create a lock 
					found_lock = new Lock(++num_locks);
					// and add it
					lock_list.add(found_lock);
				}
				else
				{
					// Get the lock associated with a transaction
					found_lock = trans.getLocks().get(0);
				}
			}
			// Acquire the lock
			found_lock.acquire(trans_id, lock_type);
			trans.addLock(found_lock);
		}
		
	}
	
	// Unlock a transaction
	public synchronized void unLock(int trans_id)
	{
		// If locking isn't enabled, exit right away
		if (!locking_enabled)
			return;
		
		// For every lock that we have
		for (Lock l : lock_list)
		{
			// Check if a transaction holds that lock
			if (l.getHolders().contains(trans_id))
			{
				l.release(trans_id);
				
			}
		}
	}
}
