	
	
public class ReadMsg extends Message
{
	public ReadMsg(int account_num)
	{
		type = "READ";
		account_id = account_num;
	}
}
