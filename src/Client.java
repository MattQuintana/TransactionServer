import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

  File config;
  String IP;
  int serverPort;
  int numTransactions;
  int numAccounts;
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


  // How to use:
  //public static void main(String[] args) {
  //  File myFile = new File("./config.txt");
  //  Client aClient = new Client(myFile);
  //}
}


