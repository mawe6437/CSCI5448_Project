
public class Vehicle {
	
	private Vehicle_Type m_type;
	private int m_year;
	private float m_value;
	private int vehicleId;
	
	public Vehicle(Vehicle_Type type, int year, float value)
	{
		m_type = type;
		m_year = year;
		m_value = value;
	}
	
	public Vehicle_Type getType() {
		return m_type;
	}
	
	public int getYear() {
		return m_year;
	}

	public float getValue() {
		return m_value;
	}
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	


}
