
import java.util.*;
import java.net.*;
import java.io.*;

public class TransactionWorker implements Runnable{
	
	private Socket transaction_socket;
	public TransactionWorker(Socket t_socket) {
		transaction_socket = t_socket;
	}
	
	@Override
	public void run()
	{
		
	}
}
