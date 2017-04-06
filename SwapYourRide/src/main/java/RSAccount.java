
import java.util.ArrayList;

public class RSAccount {
	private int m_userId;
	private String m_email;
    private String m_password;
    private Account_State m_state;
    private ArrayList<Vehicle> m_myVehicles;
    private ArrayList<Swap_Request> m_mySwapReqsSent;
    private ArrayList<Swap_Request> m_mySwapReqsRecv;
    
    protected enum Account_State
    {
    	Active, Blocked
    }
    
    public RSAccount(String _email, String _password)
    {
    	m_email = _email;
    	m_password = _password;
    }

	public int getUserId() {
		return m_userId;
	}

	public void setUserId(int userId) {
		this.m_userId = userId;
	}

	public Account_State getState() {
		return m_state;
	}

	public void setState(Account_State state) {
		this.m_state = state;
	}

	public ArrayList<Vehicle> getMyVehicles() {
		return m_myVehicles;
	}

	public void setMyVehicles(ArrayList<Vehicle> myVehicles) {
		this.m_myVehicles = myVehicles;
	}

	public ArrayList<Swap_Request> getMySwapReqsSent() {
		return m_mySwapReqsSent;
	}

	public void setMySwapReqsSent(ArrayList<Swap_Request> mySwapReqsSent) {
		this.m_mySwapReqsSent = mySwapReqsSent;
	}

	public ArrayList<Swap_Request> getMySwapReqsRecv() {
		return m_mySwapReqsRecv;
	}

	public void setMySwapReqsRecv(ArrayList<Swap_Request> mySwapReqsRecv) {
		this.m_mySwapReqsRecv = mySwapReqsRecv;
	}

	public String getEmail() {
		return m_email;
	}

	public String getPassword() {
		return m_password;
	}
	
	public RSAccount createAcct(String email, String password)
	{
		//This may need to be moved into RSAccountFactory
		
		RSAccount newAcct = new RSAccount(email, password);
		
		//Check if this account exists
		//dao.checkAcct(email, password);
		
		//Tell DAO to store this in the db
		//newAcct.setUserId = dao.insertAcct(newAcct);
		
		return newAcct;
	}
	
	public void loginAccount(String email, String password)
	{
		//This may need to be moved into RSAccountFactory
		
		//Pass email, password to dao
		//If we get userId back create account
		
		//Retrieve vehicle_lists
		
		//Notify view
	}
    
    
}
