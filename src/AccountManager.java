import java.util.*;

public class AccountManager {
	
	private List<Account> accounts_available = new ArrayList<Account>();
	// Use the account number as an index into the list
	
	
	public void createAccounts(int num_accounts, int initial_balance)
	{
		for (int i = 0; i < num_accounts; i++)
		{
			Account new_account = new Account(initial_balance);
			accounts_available.add(new_account);
		}
	}
	
	public void read(int account_num, Transaction t)
	{

	}
	
	

}
