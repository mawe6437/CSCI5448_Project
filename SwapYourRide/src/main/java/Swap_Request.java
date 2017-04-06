
public class Swap_Request {
	
	private Swap_Request_State m_state;
	private Vehicle m_initVehicle;
	private Vehicle m_targVehicle;
	private int m_swapReqId;
	
	public Swap_Request(Vehicle init_Vehicle, Vehicle targ_Vehicle)
	{
		m_state = Swap_Request_State.PENDING;
		m_initVehicle = init_Vehicle;
		m_targVehicle = targ_Vehicle;
	}

	public Swap_Request_State getState() {
		return m_state;
	}

	public void setState(Swap_Request_State state) {
		this.m_state = state;
	}

	public int getSwapReqId() {
		return m_swapReqId;
	}

	public void setSwapReqId(int swapReqId) {
		this.m_swapReqId = swapReqId;
	}

	public Vehicle getInitVehicle() {
		return m_initVehicle;
	}

	public Vehicle getTargVehicle() {
		return m_targVehicle;
	}

}
