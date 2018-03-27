
import java.net.*;
import java.io.*;
import java.util.*;



public class TransactionServer implements Runnable{
	
	public static TransactionManager t_manager = new TransactionManager();
	public static AccountManager a_manager = new AccountManager();
	public static LockManager l_manager = new LockManager();
	
	@Override
	public void run()
	{
		int port_num = 6978;
		
		
		try 
		{
			ServerSocket trans_listener = new ServerSocket(port_num);
			while(true)
			{
				// Create a new thread for every transaction that comes in
				t_manager.runTransaction(trans_listener.accept());
				
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public static void main()
	{
		TransactionServer t_server = new TransactionServer();
		t_server.run();
	}
}
