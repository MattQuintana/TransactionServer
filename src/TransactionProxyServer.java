// Who the client calls high level calls towards 
// The proxy will then do the low level network calls
// such as creating the sockets and sending the messages

import java.net.*;
import java.io.*;
import java.util.*;

public class TransactionProxyServer implements Runnable {

	Socket to_server;
	ObjectOutputStream out_stream;
	ObjectInputStream in_stream;
	// Take in the high level call
	int account1;
	int account2;
	int amount;
	
	// Connect to the transaction server
		// Package the high level call information into a message 
		// Connect to the transaction server
			// Create a socket with the port and IP of the TransactionServer
			// Maybe the Proxy should have an idea about the actual server
		// Pass the message to it 
	
	public TransactionProxyServer(String ip, int port, int acct_1, int acct_2, int amount) 
	{
		account1 = acct_1;
		account2 = acct_2;
		this.amount = amount;
		
		try 
		{
			to_server = new Socket(ip, port);
			out_stream = new ObjectOutputStream(to_server.getOutputStream());
			in_stream = new ObjectInputStream(to_server.getInputStream());
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			open();
			read(account1);
			write(account2, amount);
			write(account1, -amount);
			close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	public void open() throws Exception
	{
		OpenMsg o_msg = new OpenMsg();
		out_stream.writeObject(o_msg);
	}
	
	public void close() throws Exception
	{
		CloseMsg c_msg = new CloseMsg();
		out_stream.writeObject(c_msg);
		out_stream.close();
		to_server.close();
	}
	
	public void read(int acct_num) throws Exception
	{
		ReadMsg r_msg = new ReadMsg(acct_num);
		out_stream.writeObject(r_msg);
		int balance = (int) in_stream.readObject();
	}
	
	public void write(int acct_num, int amount) throws Exception
	{
		WriteMsg w_msg = new WriteMsg(acct_num, amount);
		out_stream.writeObject(w_msg);
	}
}
