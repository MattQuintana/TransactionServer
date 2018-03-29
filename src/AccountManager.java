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
	
	// Find the account and return it's balance
	public int read(int account_num, Transaction t)
	{
		for(Account a : accounts_available)
		{
			if (a.getID() == account_num)
			{
				
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
				if (a.getID() == account_num)
				{
					String log_s = String.format("Writing to account %d, amount %d", a.getID(), amount);
					t.log(log_s);
					a.deposit(amount);
					log_s = String.format("For account %d new balance: %d", a.getID(), a.getBalance());
				}
			}
		}
	}
	
	

}
