public class WriteMsg extends Message
{
	public WriteMsg(int account_num, int amt)
	{
		type = "WRITE";
		account_id = account_num;
		amount = amt;
		
	}
}