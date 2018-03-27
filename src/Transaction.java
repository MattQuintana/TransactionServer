
import java.util.*;

public class Transaction {

	private List<String> log = new ArrayList<String>();
	public void addLock()
	{
		
	}
	
	public void removeLock()
	{
		
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
