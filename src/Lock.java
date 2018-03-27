import java.util.stream.IntStream;

public class Lock {

	private Transaction trans;
	// Which transactions are holding the lock
	private int[] holder_ids; 
	private String lock_type;
	private boolean exist_conflict; 
	private int trans_id;
	
	public synchronized void acquire(int atrans_id, String alock_type)
	{
		while(check_conflicts())
		{
			try 
			{
				wait();
			}
			catch(InterruptedException e) 
			{
				System.out.println("EXCEPTION");
			}
		}
		if(holder_ids.length == 0)
		{
			holder_ids[0] = atrans_id; 
			trans_id = atrans_id;
			lock_type = alock_type;
		}
		else if(holder_ids.length > 0) 
		{
			if(!IntStream.of(holder_ids).anyMatch(x -> x == trans_id))
			{
				holder_ids[holder_ids.length-1] = trans_id;
			}
		}
	}

	private boolean check_conflicts()
	{	
		//false = no conflicts
		if(holder_ids.length == 0)
		{
			return false;
		}
		else
		{
			if(/*no write locks*/)
			{
				return false;
			}
		}
		return false;
	}
	
	private void release()
	{
		boolean cleared = false;
		for(int i = 0; i < holder_ids.length; i++)
		{
			if(cleared)
			{
				holder_ids[i] = holder_ids[i+1];
			}
			else if(holder_ids[i] == trans_id)
			{
				cleared = true;
				holder_ids[i] = (Integer) null;
			}
		}
	}
	
	public String getType()
	{
		return lock_type;
	}
}
