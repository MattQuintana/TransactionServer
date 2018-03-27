
import java.util.*;
import java.net.*;
import java.io.*;

public class TransactionManager{
	
	// The list of all transactions being done
	private List<Transaction> transactions_list = new ArrayList<Transaction>();
	int num_transactions = 0;
	
	// Method to call from Transaction server to run TW thread
	public void runTransaction(Socket t_socket)
	{
		(new TransactionWorker(t_socket)).run();
	}
	
	// Find a transaction based on its ID number
	public Transaction getTransaction(int t_id)
	{
		for (Transaction t : transactions_list)
		{
			if (t.getID() == t_id)
			{
				return t;
			}
		}
		return null;
	}
	
	// Remove a transaction based on its ID number
	public void removeTransaction(int t_id) 
	{
		for (Transaction t : transactions_list)
		{
			if (t.getID() == t_id)
			{
				transactions_list.remove(t);
			}
		}
	}
	
	// Return the list of transactions that are running
	public List<Transaction> getTransactionList()
	{
		return transactions_list;
	}
	
	private class TransactionWorker implements Runnable{
		
		private Socket transaction_socket;
		private int t_id;
		private Message msg; 
		public TransactionWorker(Socket t_socket) {
			transaction_socket = t_socket;
			t_id = transactions_list.size();
		}
		
		@Override
		public void run()
		{
			try 
			{
				ObjectInputStream in_stream = new ObjectInputStream(transaction_socket.getInputStream());
				Message msg = (Message) in_stream.readObject();
				
				
			}
			catch(Exception e)
			{
				System.out.println("Issue with running the TransactionWorker thread.");
				System.out.println(e);
			}
			
		}
		
		
	}
	
	
}
