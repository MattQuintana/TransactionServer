public class WriteMsg extends Message
{
	public WriteMsg(int account_num)
	{
		type = "WRITE";
		account_id = account_num;
	}
}