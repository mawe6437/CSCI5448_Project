package spring.model;

public class Swap_Request {
	
	private String state;
	private Vehicle initVehicle;
	private Vehicle targVehicle;
	private int swapReqId;
	
	public Swap_Request(Vehicle init_Vehicle, Vehicle targ_Vehicle)
	{
		state = "PENDING";
		initVehicle = init_Vehicle;
		targVehicle = targ_Vehicle;
	}

	public String getState() {
		return state;
	}

	public void setState(String _state) {
		this.state = _state;
	}

	public int getSwapReqId() {
		return swapReqId;
	}

	public void setSwapReqId(int swapReqId) {
		this.swapReqId = swapReqId;
	}

	public Vehicle getInitVehicle() {
		return initVehicle;
	}

	public Vehicle getTargVehicle() {
		return targVehicle;
	}

}
