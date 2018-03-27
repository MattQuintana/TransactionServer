
public class Account {
	
	int account_num;
	int balance;
	
	public Account(int init_balance)
	{
		balance = init_balance;
	}
	
	public void setBalance(int new_balance)
	{
		balance = new_balance;
	}
	
	public int getBalance()
	{
		return balance;
	}
	
	public void deposit(int value)
	{
		balance += value;
	}
	
	public void withdraw(int value)
	{
		balance -= value;
	}
	
	public int getID()
	{
		return account_num;
	}
}
