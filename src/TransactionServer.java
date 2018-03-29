import java.net.*;
import java.io.*;
import java.util.*;


public class TransactionServer implements Runnable{
  // multi threaded proxy catches?
  File config;
  String IP;
  int serverPort;
  int numTransactions;
  int numAccounts;
  int initialBalance;
  boolean lockingEnabled;

  public TransactionServer(File someConfigFile) {
    this.config = someConfigFile;
    int resultOfReadingConfig = attemptReadConfig(this.someConfigFile);
    if (resultOfReadingConfig == 0) {
      System.out.println("Read config file successfully!");
    } else {
      System.out.println("Didn't read config file.");
    }
  }
  public int attemptReadConfig(File file) {
    try {
      Scanner sc = new Scanner(file);
      System.out.println("Attempting file parse...");
      int lineNumber = 0;
      while(sc.hasNextLine()){
        String line = sc.nextLine();
        String[] details = line.split(": ");
        if (lineNumber == 0) {
          this.IP = details[1];
          lineNumber++;
        } else if (lineNumber == 1) {
          this.serverPort = Integer.parseInt(details[1]);
          lineNumber++;
        } else if (lineNumber == 2) {
          this.numTransactions = Integer.parseInt(details[1]);
          lineNumber++;
        } else if (lineNumber == 3) {
          this.numAccounts = Integer.parseInt(details[1]);
          lineNumber++;
        } else if (lineNumber == 4) {
          this.initialBalance = Integer.parseInt(details[1]);
          lineNumber++;
        } else if (lineNumber == 5) {
          this.lockingEnabled = Boolean.parseBoolean(details[1]);
          lineNumber++;
        }
      }
      return 0;
    } catch (FileNotFoundException e) {
      System.out.println("Configuration file does not exsist.");
      e.printStackTrace();
      return 1;
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Improperly configured file.");
      e.printStackTrace();
      return 1;
    }
  }

	public static TransactionManager t_manager = new TransactionManager();
	public static AccountManager a_manager = new AccountManager();
	public static LockManager l_manager = new LockManager();


	
	@Override
	public void run()
	{
//		int port_num = 6978;
		System.out.println(String.format("Listening on port %d", t_server.port_num));
		
		try 
		{
			ServerSocket trans_listener = new ServerSocket(t_server.port_num);
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
	
	public static void main(String[] args)
	{
<<<<<<< HEAD
		TransactionServer t_server = new TransactionServer();
		a_manager.createAccounts(10, 10);
		l_manager.enableLocking(true);
=======
    File aConfigFile = new File("./config.txt");
		TransactionServer t_server = new TransactionServer(aConfigFile);
    a_manager.createAccounts(t_server.numAccounts, t_server.initialBalance)

    if (t_server.enableLocking) {
    	l_manager.enableLocking(true);
    } else {
      l_manager.enableLocking(false);
    }
>>>>>>> 5b80a12bd074c5418cfddc3e0d9e398ceb614c96
		t_server.run();
	}
}

