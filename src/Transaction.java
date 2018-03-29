
import java.util.*;

public class Transaction {

	private List<String> log = new ArrayList<String>();
	public List<Lock> locks = new ArrayList<Lock>();
	private String type;
	
	private int t_id;
	
	public Transaction(int id) {
		t_id = id;
	}
	
	public int getID()
	{
		return t_id;
	}
	
	public List<Lock> getLocks()
	{
		return locks;
	}
	
	public void addLock(Lock new_lock)
	{
		locks.add(new_lock);
	}
	
	public void removeLock(Lock r_lock)
	{
		for (Lock cur_lock : locks)
		{
			if (cur_lock.equals(r_lock))
			{
				locks.remove(cur_lock);
			}
		}
	}
	
	public void log(String log_msg)
	{
		log.add(log_msg);
	}
	
	public void display_log()
	{
		System.out.println(Arrays.toString(log.toArray()));
	}
}
