import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Client {

  File config;
  String IP;
  int serverPort;
  int numTransactions;
  int numAccounts;
  int initialBalance;
  boolean lockingEnabled;

  public Client(File starterFile) {
     // This constructor has one parameter, file.
    this.config = starterFile;

    int resultOfReadingConfig = attemptReadConfig(this.config);
    if (resultOfReadingConfig == 0) {
      System.out.println("Read config file successfully!");
    } else {
      System.out.println("Didn't read config file.");
    }
    
    // Create the random generator
    Random rand = new Random();
    for (int i = 0; i < this.numTransactions; i++) {
      // Create the proxy thread to talk through to the server
	  // Get the accounts to work with
	  int acct_1 = rand.nextInt(numAccounts);
      int acct_2 = rand.nextInt(numAccounts);
      // Get random number from 0 to account balance      
      int amount = rand.nextInt(initialBalance);
      
      
      
      TransactionProxyServer tps = new TransactionProxyServer(this.IP, this.serverPort);
      tps.start();
      
      try 
      {
    	  open(tps);
          read(tps, acct_1);
          write(tps, acct_2, amount);
          write(tps, acct_1, -amount);
          close(tps);
      }
      catch(Exception e)
      {
    	  System.out.println(e);
      }
      
      
    }
    
    TransactionServer.a_manager.displayAccountsInfo();
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

  // High level open to proxy
  public void open(TransactionProxyServer tps) throws Exception
  {
	  tps.open();
  }
  
  // High level close to proxy
  public void close(TransactionProxyServer tps) throws Exception
  {
	  tps.close();
  }
  
  // High level read to proxy
  public void read(TransactionProxyServer tps, int account_num) throws Exception
  {
	  tps.read(account_num);
  }
  
  // High level write command to proxy
  public void write(TransactionProxyServer tps, int account_num, int amount) throws Exception
  {
	  tps.write(account_num, amount);
  }
  
  // How to use:
  public static void main(String[] args) {
    File myFile = new File("config.txt");
    Client aClient = new Client(myFile);
  }
}


