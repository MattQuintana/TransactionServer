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
				return a.getBalance();
			}
		}
		return -1;
	}
	
	

}
