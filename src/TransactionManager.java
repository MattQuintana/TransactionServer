
import java.util.*;

public class TransactionManager implements Runnable{

	// The account manager that all of its transactions will have to communicate with
	private AccountManager a_manager;
	
	// The list of all transactions being done
	private List<TransactionWorker> transactions_list = new ArrayList<TransactionWorker>();
	
	
	public void setAccountManager(AccountManager manager)
	{
		a_manager = manager;
	}
	
	@Override
	public void run()
	{
		// Do whatever the worker needs to do here 
		// Translate the low level network calls to API calls with the AccountManager
		// Make sure to tell the AccountManager which account we want to work with and 
		// what we want to do with the account
	}
}
