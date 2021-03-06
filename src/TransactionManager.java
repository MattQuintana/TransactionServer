
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
	public void removeTransaction(Transaction t) 
	{
		transactions_list.remove(t);
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
		private Transaction t;
		public TransactionWorker(Socket t_socket) {
			transaction_socket = t_socket;
			t_id = transactions_list.size();
		}
		
		@Override
		public void run()
		{
			try 
			{
				//System.out.println("In transaction worker.");
				ObjectInputStream in_stream = new ObjectInputStream(transaction_socket.getInputStream());
				ObjectOutputStream out_stream = new ObjectOutputStream(transaction_socket.getOutputStream());
				boolean open_t = true;
				
				while (open_t)
				{
					Message msg = (Message) in_stream.readObject();
					switch (msg.type)
					{
						case "OPEN":
							//System.out.println("Opening Transaction");
							num_transactions++;
							t = new Transaction(num_transactions);
							t.log(String.format("Transaction %d opened. \n", t.getID()));
							transactions_list.add(t);
							break;
						case "CLOSE":
							TransactionServer.l_manager.unLock(t.getID());
							t.log("Closing transaction. \n");
							removeTransaction(t);
							in_stream.close();
							transaction_socket.close();
							t.display_log();
							open_t = false;
							break;
						case "READ":
							//System.out.println("Reading transaction");
							int balance = TransactionServer.a_manager.read(msg.account_id, t);
							// Write the balance back to the proxy
							out_stream.writeObject(balance);
							break;
						case "WRITE":
							//System.out.println("Writing transaction");
							TransactionServer.a_manager.write(msg.account_id, msg.amount, t);
							
							break;
					}
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Issue with running the TransactionWorker thread.");
				System.out.println(e);
			}
		}
	}
}
