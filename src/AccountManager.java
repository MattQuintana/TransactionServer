import java.util.*;

public class AccountManager {
	
	private List<Account> accounts_available = new ArrayList<Account>();
	// Maybe create this into a dictionary/hashmap so that we can access 
	// account directly instead of searching through the list
	
	private TransactionManager t_manager;
	private LockManager l_manager;
	
	public void setLockManager(LockManager manager)
	{
		l_manager = manager;
	}

}
