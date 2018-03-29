
import java.util.*;

public class Transaction {

	private List<String> log = new ArrayList<String>();
	public List<Lock> locks = new ArrayList<Lock>();
	private String type;
	
	private int t_id;
	
	public Transaction(int id) {
		t_id = id;
	}
	
	// Get the transaction ID
	public int getID()
	{
		return t_id;
	}
	
	// Return the list of locks currently associated with the transaction
	public List<Lock> getLocks()
	{
		return locks;
	}
	
	// Add a lock to the transaction
	public void addLock(Lock new_lock)
	{
		locks.add(new_lock);
		log(String.format("Adding %s lock \n", new_lock.getType()));
	}
	
	// Remove a lock from the list of locks associated with the transaction
	public void removeLock(Lock r_lock)
	{
		
		log(String.format("Removing %s lock \n", r_lock.getType()));
		locks.remove(locks.indexOf(r_lock));

	}
	
	// Create a log of things that have occured to the transaction
	public void log(String log_msg)
	{
		log.add(log_msg);
	}
	
	// Display the messages in the log
	public void display_log()
	{
		System.out.println(Arrays.toString(log.toArray()));
	}
}
