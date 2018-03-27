
import java.util.*;
import java.net.*;

public class TransactionManager implements Runnable{
	
	// The list of all transactions being done
	private List<TransactionWorker> transactions_list = new ArrayList<TransactionWorker>();
	
	public void runTransaction(Socket t_socket)
	{
		(new TransactionWorker(t_socket)).run();
	}
	
	@Override
	public void run()
	{
		// Do whatever the worker needs to do here 
		// Translate the low level network calls to API calls with the AccountManager
		// Make sure to tell the AccountManager which account we want to work with and 
		// what we want to do with the account
	}
	
	private class TransactionWorker implements Runnable{
		
		private Socket transaction_socket;
		public TransactionWorker(Socket t_socket) {
			transaction_socket = t_socket;
		}
		
		@Override
		public void run()
		{
			
		}
	}
}
