
import java.util.*;

public class LockManager {
	
	// List of locks that are being used
	private List<Lock> lock_list = new ArrayList<Lock>();
	private boolean locking_enabled;
	
	
	public void enableLocking(boolean state)
	{
		locking_enabled = state;
	}
	
	public void setLock(Account a, int trans_id, String lock_type)
	{
		// If locking is enabled in the first place
		if (locking_enabled)
		{
			Lock found_lock;
			synchronized(this) 
			{
				
			}
		}
		
	}
}
