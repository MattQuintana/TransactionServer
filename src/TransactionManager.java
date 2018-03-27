
import java.util.*;
import java.net.*;
import java.io.*;

public class TransactionManager{
	
	// The list of all transactions being done
	private List<TransactionWorker> transactions_list = new ArrayList<TransactionWorker>();
	
	public void runTransaction(Socket t_socket)
	{
		(new TransactionWorker(t_socket)).run();
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
