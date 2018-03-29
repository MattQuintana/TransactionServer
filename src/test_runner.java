
import java.net.*;
import java.io.*;

public class test_runner {
	
	public static void main(String[] args)
	{
		try 
		{
			// Create a number of accounts to test with
			//TransactionServer.a_manager.createAccounts(10, 10);
			
			// Create a socket to the server 
			Socket c_socket = new Socket("localhost", 6978);
			// Create a stream from the socket to send the objects over
			ObjectOutputStream o_stream = new ObjectOutputStream(c_socket.getOutputStream());
			
			// Creating an open msg
			OpenMsg o_msg = new OpenMsg();
			// Create a read msg; ReadMsg(account_num)
			ReadMsg r_msg = new ReadMsg(5);
			// Create a write msg; WriteMsg(account_num, amount)
			WriteMsg w_msg = new WriteMsg(5, 6);
			// Create a close message; 
			CloseMsg c_msg = new CloseMsg();
			
			// Sending an object over the stream 
			o_stream.writeObject(o_msg);
			o_stream.writeObject(r_msg);
			o_stream.writeObject(w_msg);
			o_stream.writeObject(c_msg);
			
			// Closing the sockets
			o_stream.close();
			c_socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
