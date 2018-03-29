import java.util.*;

public class AccountManager {
	
	private List<Account> accounts_available = new ArrayList<Account>();
	// Use the account number as an index into the list
	
	// Create all of the initial accounts and set their balance
	public void createAccounts(int num_accounts, int initial_balance)
	{
		for (int i = 0; i < num_accounts; i++)
		{
			Account new_account = new Account(initial_balance);
			new_account.setID(i);
			accounts_available.add(new_account);
		}
	}
	
	// Show the balances of all the accounts
	public void displayAccountsInfo()
	{
		for (Account a : accounts_available)
		{
			System.out.println(String.format("Account %d: %d", a.getID(), a.getBalance()));
		}
	}
	
	public int getTotal()
	{
		int total = 0;
		for (Account a : accounts_available)
		{
			total += a.getBalance();
		}
		return total;
	}
	
	// Find the account and return it's balance
	public int read(int account_num, Transaction t)
	{
		for(Account a : accounts_available)
		{
			if (a.getID() == account_num)
			{
				TransactionServer.l_manager.setLock(t, t.getID(), "READ");
				String log_s = String.format("Read the balance of account %d. Has current balance of %d \n", a.getID(), a .getBalance());
				t.log(log_s);
				return a.getBalance();
				
			}
		}
		return -1;
	}
	
	public void write(int account_num, int amount, Transaction t)
	{
		for(Account a: accounts_available)
		{
			if (a.getID() == account_num)
			{
				TransactionServer.l_manager.setLock(t, t.getID(), "WRITE");
				String log_s = String.format("Writing to account %d, amount %d \n", a.getID(), amount);
				t.log(log_s);
				a.deposit(amount);
				log_s = String.format("For account %d new balance: %d \n", a.getID(), a.getBalance());
				t.log(log_s);
				break;
			}
		}
	}

}
