package spring.model;

public class Vehicle {
	//CAR = 1, TRUCK = 2, MOTORCYCLE = 3, BOAT = 4
	private String type;
	private String year;
	private String value;
	private int vehicleId;
	private int userId;
	
		public Vehicle()
	{
		
	}
	public Vehicle(String _type, String _year, String _value)
	{
		type = _type;
		year = _year;
		value = _value;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	
	public String getYear() {
		return year;
	}

	public String getValue() {
		return value;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
