

public class Message {
	private String type;
	int account_id;
	
	
	public class ReadMsg
	{
		public ReadMsg(int account_num)
		{
			type = "READ";
			account_id = account_num;
		}
	}
	
	public class WriteMsg
	{
		public WriteMsg(int account_num)
		{
			type = "WRITE";
			account_id = account_num;
		}
	}
	
	public class OpenMsg
	{
		public OpenMsg()
		{
			type = "OPEN";
		}
	}
	
	public class CloseMsg
	{
		public CloseMsg()
		{
			type = "CLOSE";
		}
	}
	
}


