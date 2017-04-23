package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spring.model.*;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
 

public class RSAccountDAO {
 
    private JdbcTemplate jdbcTemplate;
 
    public RSAccountDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
 
    // ACCOUNT DAO CODE

    public void insertAcct(RSAccount account)
    {
        String sql = "INSERT INTO rsaccount (email, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, account.getEmail(), account.getPassword());

    }
 

    public void updateAcct(RSAccount account)
    {
        String sql = "UPDATE account SET email=?, password=?, WHERE userId=?";
            jdbcTemplate.update(sql, account.getEmail(), account.getPassword(), account.getUserId());
        
    }
    
    public void updateAcct(RSAccount account, boolean block)
    {
        String sql = "UPDATE account SET isBlocked=? WHERE userId=?";
            jdbcTemplate.update(sql, block, account.getUserId());
        
    }
        
    public RSAccount loginAcct(String email, String password)
    {
       	String sql = "SELECT * FROM rsaccount WHERE email=? AND password=?";
        return jdbcTemplate.query(sql, new Object[] { email, password }, new ResultSetExtractor<RSAccount>() 
        {
            @Override
            public RSAccount extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) {
                	RSAccount account;
                	if(rs.getBoolean("isAdmin"))
                	{
                        account = new RSAdminAccount(rs.getString("email"), rs.getString("password"));
                	}
                	else
                	{
                		account = new RSAccount(rs.getString("email"), rs.getString("password"));	
                	}
                	
                    account.setUserId(rs.getInt("userId"));
                    account.setIsBlocked(rs.getBoolean("isBlocked"));
                    return account;
                }
     
                return null;
            }
                
        });
    }
    
    public void updateAccountBlock(int userId, boolean blocked)
    {
        String sql = "UPDATE account SET isBlocked=?, WHERE userId=?";
        jdbcTemplate.update(sql, blocked, userId);
    }
    
    // VEHICLE DAO CODE
    
    public void insertVehicle(Vehicle vehicle, int userId)
    {
        String sql = "INSERT INTO vehicles (year, value, userId, type) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, vehicle.getYear(), vehicle.getValue(), userId, vehicle.getType());

    }
    
    public void removeVehicle(Vehicle vehicle, int userId)
    {
        String sql = "DELETE FROM vehicles WHERE userId=? AND year=? AND value=? AND type=?";
        jdbcTemplate.update(sql, userId, vehicle.getYear(), vehicle.getValue(), vehicle.getType());

    }
  
    public ArrayList<Vehicle> getVehicles(int userId)
    {
       	String sql = "SELECT * FROM vehicles WHERE userId=?";
        return jdbcTemplate.query(sql, new Object[] { userId }, new ResultSetExtractor<ArrayList<Vehicle>>() 
        {
            @Override
            public ArrayList<Vehicle> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
            	ArrayList<Vehicle> vlist = new ArrayList<Vehicle>();
                while (rs.next()) {
                	Vehicle tempVehicle;
                	//Vehicle_Type vType = rs.getInt("v_type");
                	tempVehicle = new Vehicle(rs.getString("type"), rs.getString("year"), rs.getString("value"));
                	tempVehicle.setVehicleId(rs.getInt("vehicleId"));
                	tempVehicle.setUserId(rs.getInt("userId"));
                    vlist.add(tempVehicle);                	
                 }
                 return vlist;
            }
                
        });
    }
    
    public Vehicle getVehicle(int vehicleId)
    {
       	String sql = "SELECT * FROM vehicles WHERE vehicleId=?";
        return jdbcTemplate.query(sql, new Object[] { vehicleId }, new ResultSetExtractor<Vehicle>() 
        {
            @Override
            public Vehicle extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
                if (rs.next()) {
                	Vehicle tempVehicle;
                	tempVehicle = new Vehicle(rs.getString("type"), rs.getString("year"), rs.getString("value"));
                	tempVehicle.setVehicleId(rs.getInt("vehicleId"));
                	tempVehicle.setUserId(rs.getInt("userId"));
                    return tempVehicle;             	
                 }
                 return null;
            }
                
        });
    }
    
    public ArrayList<Vehicle> searchVehicles(int userId)
    {
       	String sql = "SELECT * FROM vehicles WHERE userId!=?";
        return jdbcTemplate.query(sql, new Object[] { userId }, new ResultSetExtractor<ArrayList<Vehicle>>() 
        {
            @Override
            public ArrayList<Vehicle> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
            	ArrayList<Vehicle> vlist = new ArrayList<Vehicle>();
                while (rs.next()) {
                	Vehicle tempVehicle;
                	tempVehicle = new Vehicle(rs.getString("type"), rs.getString("year"), rs.getString("value"));
                	tempVehicle.setVehicleId(rs.getInt("vehicleId"));
                	tempVehicle.setUserId(rs.getInt("userId"));
                    vlist.add(tempVehicle);                	
                 }
                 return vlist;
            }
                
        });
    }
    
    // SWAP_REQUEST DAO CODE
    
    public ArrayList<Swap_Request> getSwapReqsSent(int userId)
    {
       	String sql = "SELECT * FROM swap_request WHERE inituserId=?";
        return jdbcTemplate.query(sql, new Object[] { userId }, new ResultSetExtractor<ArrayList<Swap_Request>>() 
        {
            @Override
            public ArrayList<Swap_Request> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
            	ArrayList<Swap_Request> vlist = new ArrayList<Swap_Request>();
                while (rs.next()) {
                	Swap_Request tempReq;
                	Vehicle initVehicle = getVehicle(rs.getInt("initVehicleId"));
                	Vehicle targVehicle = getVehicle(rs.getInt("targVehicleId"));
                	tempReq = new Swap_Request(initVehicle, targVehicle);
                	//set state and reqId
                	tempReq.setSwapReqId(rs.getInt("swapreqId"));
                	tempReq.setState(rs.getString("state"));		
                    vlist.add(tempReq);                	
                 }
                 return vlist;
            }
                
        });
    }
    
    public ArrayList<Swap_Request> getSwapReqsRecv(int userId)
    {
       	String sql = "SELECT * FROM swap_request WHERE targuserId=?";
        return jdbcTemplate.query(sql, new Object[] { userId }, new ResultSetExtractor<ArrayList<Swap_Request>>() 
        {
            @Override
            public ArrayList<Swap_Request> extractData(ResultSet rs) throws SQLException, DataAccessException 
            {
            	ArrayList<Swap_Request> vlist = new ArrayList<Swap_Request>();
                while (rs.next()) {
                	Swap_Request tempReq;
                	Vehicle initVehicle = getVehicle(rs.getInt("initVehicleId"));
                	Vehicle targVehicle = getVehicle(rs.getInt("targVehicleId"));
                	tempReq = new Swap_Request(initVehicle, targVehicle);
                	//set state and reqId
                	tempReq.setSwapReqId(rs.getInt("swapreqId"));
                	tempReq.setState(rs.getString("state"));		
                    vlist.add(tempReq);                	
                 }
                 return vlist;
            }
                
        });
    }
        
    public void updateSwapReq(int swapReqId, String state)
    {
           	String sql = "UPDATE swap_request SET state=? WHERE swapReqId=?";
            jdbcTemplate.update(sql, state, swapReqId);
            
            // Log the change here
    }
    
    public void deleteSwapReq(int swapReqId)
    {
           	String sql = "DELETE FROM swap_request WHERE swapReqId=?";
            jdbcTemplate.update(sql, swapReqId);
            
            // Log the change here
    }
    
    public void insertSwapReq(int initVehicleId, int targVehicleId, int initUserId, int targUserId)
    {
           	String sql = "INSERT INTO swap_request (initVehicleId, targVehicleId, inituserId, targuserId, state) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql, initVehicleId, targVehicleId, initUserId, targUserId, "PENDING");            
            // Log the change here
    }
}