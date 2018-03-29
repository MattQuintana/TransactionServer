import java.util.stream.IntStream;
import java.util.*;

public class Lock {

	private Transaction trans;
	// Which transactions are holding the lock
	private List<Integer> holder_ids = new ArrayList<Integer>(); 
	private String lock_type;
	private boolean exist_conflict; 
	
	public synchronized void acquire(int atrans_id, String alock_type)
	{
		System.out.println("Acquire lock");
		trans = TransactionServer.t_manager.getTransaction(atrans_id);
		// Check if there are any conflicts
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
		// Check if there are no hold locks on the transaction
		// If there are no locks
		if(holder_ids.size() == 0)
		{
			// Add the id to our holders
			holder_ids.add(atrans_id); 
			lock_type = alock_type;
		}
		// If another account is holding onto the lock, it will share the lock
		else if(holder_ids.size() > 0) 
		{
			// If the holder id is not in the holder IDs 
			if(!holder_ids.contains(atrans_id))
			{
				// Add it in
				holder_ids.add(atrans_id);
			}
			// If this transaction is already a holder and we need to make it a write
			else
			{
				if (alock_type == "WRITE")
				{
					// promote the lock
					lock_type = alock_type;
				}
			}
		}
	}

	private boolean check_conflicts()
	{	
		//false = no conflicts
		if(trans.locks.isEmpty())
		{
			return false;
		}
		else
		{
			for(int i = 0; i < trans.locks.size(); i++)
			{
				if(trans.locks.get(i).getType() == "WRITE")
				{
					return true;
				}
			}
			return false;
		}
	}
	
	public synchronized void release(int t_id)
	{
		System.out.println("Release lock");
		boolean cleared = false;
		for(int i = 0; i < holder_ids.size(); i++)
		{
			if (holder_ids.get(i) == null)
			{
				continue;
			}
			if(cleared)
			{
				try 
				{
					holder_ids.get(i+1);
					holder_ids.set(i, holder_ids.get(i+1));
					
				}
				catch(IndexOutOfBoundsException e)
				{
					break;
				}
			}
			else if(holder_ids.get(i) == t_id)
			{
				cleared = true;
				holder_ids.set(i, 0);
				
				try
				{
					holder_ids.get(i+1);
					holder_ids.set(i, holder_ids.get(i+1));
					
				}
				catch(IndexOutOfBoundsException e)
				{
					break;
				}
				
			}
			
		}
		lock_type = "NONE";
		notifyAll();
	}
	
	public String getType()
	{
		return lock_type;
	}
	
	// Get the list of transaction ID's that are holding onto the lock
	public List<Integer> getHolders()
	{
		return holder_ids;
	}
}
