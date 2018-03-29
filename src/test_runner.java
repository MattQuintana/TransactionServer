
import java.net.*;
import java.io.*;

public class test_runner {
	
	public static void main(String[] args)
	{
		try 
		{
			TransactionServer.a_manager.createAccounts(10, 10);
			Socket c_socket = new Socket("localhost", 6978);
			ObjectOutputStream o_stream = new ObjectOutputStream(c_socket.getOutputStream());
			
			OpenMsg o_msg = new OpenMsg();
			WriteMsg w_msg = new WriteMsg(5, 6);
			
			o_stream.writeObject(o_msg);
			o_stream.writeObject(w_msg);
			
			
			o_stream.close();
			c_socket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
