
public class Log_Entry {
	private int m_userId;
	private int m_swapReqId;
	private Swap_Request_State m_swapReqState;
	
	public int getM_userId() {
		return m_userId;
	}

	public int getM_swapReqId() {
		return m_swapReqId;
	}

	public Swap_Request_State getM_swapReqState() {
		return m_swapReqState;
	}


	public Log_Entry(int userId, int swapReqId, Swap_Request_State swapReqState)
	{
		m_userId = userId;
		m_swapReqId = swapReqId;
		m_swapReqState = swapReqState;
	}

}
