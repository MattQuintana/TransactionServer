
import java.util.*;

public class LockManager {
	
	// List of locks that are being used
	private List<Lock> lock_list = new ArrayList<Lock>();
	
	// Create a new lock to be used
	public Lock acquireLock() 
	{
		Lock new_lock = new Lock();
		
		// Add the lock to the list
		lock_list.add(new_lock);
		
		// Return the lock to who ever needed it
		return new_lock;
	}
	
	// Release a lock that is no longer needed
	public void releaseLock(Lock released_lock)
	{
		// Find the lock in the list and remove it from the list
	}
	
}
