
public class Lock {
	boolean is_locked = false;
	int lock_num;
	
	// Might want to create a constructor that takes in an int
	// that will serve as the lock num/ID of the lock
	
	public void lock()
	{
		is_locked = true;
	}
	
	public void unlock()
	{
		is_locked = false;
	}
	
	public int getLockNum()
	{
		return lock_num;
	}
}
