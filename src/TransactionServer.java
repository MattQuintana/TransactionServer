
import java.net.*;
import java.io.*;
import java.util.*;



public class TransactionServer {
	
	public TransactionManager t_manager = new TransactionManager();
	
	public static void main()
	{
		int port_num = 6978;;
		
		try 
		{
			ServerSocket trans_listener = new ServerSocket(port_num);
			while(true)
			{
				// Create a new thread for every transaction that comes in
				// Probably create a new transaction object and from there 
				// create a transaction worker thread. 
				
				// Alternatively just create the transaction worker from here.
				
				// Atomic creation of thread on the socket accept
			}
		}
		catch(Exception e)
		{
			
		}
	}
}
