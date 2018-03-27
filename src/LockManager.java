
import java.util.*;

public class LockManager {
	
	// List of locks that are being used
	private List<Lock> lock_list = new ArrayList<Lock>();
	private boolean locking_enabled;
	
	
	public void enableLocking(boolean state)
	{
		locking_enabled = state;
	}
	
	@SuppressWarnings("null")
	public void setLock(Transaction trans, int trans_id, String lock_type)
	{
		// If locking is enabled in the first place
		if (locking_enabled)
		{
			Lock found_lock;
			synchronized(this) 
			{
				if(trans.locks.isEmpty())
				{
					found_lock = new Lock();
					trans.locks.add(found_lock);
					lock_list.add(found_lock);
				}
				else
				{
					found_lock = trans.locks.get(0);
				}
			}
			found_lock.acquire(trans_id, lock_type);
		}
		
	}
}
