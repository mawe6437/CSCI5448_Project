
public class RSAccountFactory {
	public RSAccount createAccount(String email, String password)
	{
		//Interact with dao
		//Check if account exists
		//dao.checkAccount(email, password)
		//if(true)
		// notify view
		// return null
		//else
		RSAccount newAccount = new RSAccount(email, password);
		
		//insert into dao
		//newAccount.setUserId(dao.insertAccount(newAccount));
		//newAccount.setState(ACTIVE);
		return newAccount;
		
	}
}
