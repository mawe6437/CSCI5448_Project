package spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import spring.dao.RSAccountDAO;
import spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RSController
{
  
  @Autowired
  private RSAccountDAO rsaccountDAO;
  private RSAccount  rsAccount;
  private Vehicle  targVehicle;
  private Vehicle  initVehicle;
  private String loginMsg = "";
  
  @RequestMapping(value="/")
  public ModelAndView listContact(ModelAndView model) throws IOException{
  	  System.out.println("Made it to default handler");
  	  LoginDetail loginDetail = new LoginDetail();
      model.addObject("login", loginDetail);
      model.addObject("loginMsg", loginMsg);
      model.setViewName("home");
   
      return model;
  }
  
  @RequestMapping(value = "/newAccount", method = RequestMethod.GET)
  public ModelAndView newAccount(ModelAndView model) {
  	  System.out.println("Made it to newaccount handler");
  	  LoginDetail loginDetail = new LoginDetail();
  	  model.addObject("login", loginDetail);
      model.setViewName("join");
      return model;
  }
  
  @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
  public ModelAndView loginUser(@ModelAttribute LoginDetail loginDetail) {
      rsAccount = rsaccountDAO.loginAcct(loginDetail.getEmail(), loginDetail.getPassword());
  	  if(rsAccount != null)
  	  {	    
  	    
  		    if (rsAccount.getIsBlocked()) 
  		    { 
  		    	loginMsg = "Account is Blocked";
  		    	ModelAndView model = new ModelAndView("redirect:/");
  		    	model.addObject("loginMsg", loginMsg);
  		    	return model; 
  		    }
  		    
  		    if (rsAccount instanceof RSAdminAccount)
  		    {
  		    	// add model attributes for Admin here
  		    	ModelAndView model = new ModelAndView("adminAccountLogin");
  		    	model.addObject("email", rsAccount.getEmail());
  		    	return model;
  		    }
  		    else
  		    {
  		    	return loginMain(null);
  		    	/*
  		    	int userId = rsAccount.getUserId();
  		    	
  		    	// Retrieve vehicles, swap requests, add Attributes
  		    	List<Vehicle> vlist = rsaccountDAO.getVehicles(userId);
  		    	
  		    	List<Swap_Request> srSent = rsaccountDAO.getSwapReqsSent(userId);
  		    	// Create a vehicle list related to these swaps
  		    	
  		    	List<Swap_Request> srRecv = rsaccountDAO.getSwapReqsRecv(userId);
  		        // Create a vehicle list related to these swaps
  		    	
  			    ModelAndView model = new ModelAndView("accountLogin");
  			    model.addObject("email", rsAccount.getEmail());
  			    // Vehicle List
  		        model.addObject("vehicleList", vlist);
  		        // Object to add vehicle
  		 	    Vehicle vehicle = new Vehicle();
  		  	    model.addObject("vehicle", vehicle);
  		  	    // Swap Req Sent List
  		        model.addObject("srSentList", srSent);
  		        // Swap Req Recv List
  		        model.addObject("srRecvList", srRecv);
  		    	return model;
  		    	*/
  		    }
  	 
  		}
  		else
  		{
  			loginMsg = "Email/Password incorrect";
		    ModelAndView model = new ModelAndView("redirect:/");
		    model.addObject("loginMsg", loginMsg);
		    return model; 
  		}
  }
  
  @RequestMapping(value = "/loginMain", method = RequestMethod.GET)
  public ModelAndView loginMain(ModelAndView oldmodel) {
	  int userId = rsAccount.getUserId();
    	
    	// Retrieve vehicles, swap requests, add Attributes
    	List<Vehicle> vlist = rsaccountDAO.getVehicles(userId);
    	
    	List<Swap_Request> srSent = rsaccountDAO.getSwapReqsSent(userId);
    	// Create a vehicle list related to these swaps
    	
    	List<Swap_Request> srRecv = rsaccountDAO.getSwapReqsRecv(userId);
        // Create a vehicle list related to these swaps
    	
	    ModelAndView model = new ModelAndView("accountLogin");
	    model.addObject("email", rsAccount.getEmail());
	    // Vehicle List
        model.addObject("vehicleList", vlist);
        // Object to add vehicle
 	    Vehicle vehicle = new Vehicle();
  	    model.addObject("vehicle", vehicle);
  	    // Swap Req Sent List
        model.addObject("srSentList", srSent);
        // Swap Req Recv List
        model.addObject("srRecvList", srRecv);
    	return model; 
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public ModelAndView logout(ModelAndView model) {
  	  System.out.println("Made it to logout handler");
  	  rsAccount = null;
  	  LoginDetail loginDetail = new LoginDetail();
  	  model.addObject("login", loginDetail);
      model.setViewName("home");
      return model;
  }
  
  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public ModelAndView addUser(@ModelAttribute LoginDetail loginDetail) {
	  System.out.println("Made it to adduser handler");
	  rsAccount = new RSAccount(loginDetail.getEmail(), loginDetail.getPassword());
      rsaccountDAO.insertAcct(rsAccount);
      return new ModelAndView("redirect:/");
  }
  
  @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
  public ModelAndView addVehicle(@ModelAttribute Vehicle vehicle) {
	  System.out.println("Made it to addvehicle handler");
	//  Vehicle vehicle = new Vehicle(Vehicle_Type.values()[type], year, value);
	  rsaccountDAO.insertVehicle(vehicle, rsAccount.getUserId());
	  ModelAndView model = new ModelAndView("redirect:/loginMain");
	  return  model;
  }
  
  @RequestMapping(value = "/deleteVehicle", method = RequestMethod.GET)
  public ModelAndView deleteVehicle(HttpServletRequest request) {
	  System.out.println("Made it to addvehicle handler");
	  int vehicleId = Integer.parseInt(request.getParameter("id"));
      Vehicle tempVehicle = rsaccountDAO.getVehicle(vehicleId);
	//  Vehicle vehicle = new Vehicle(Vehicle_Type.values()[type], year, value);
	  rsaccountDAO.removeVehicle(tempVehicle, rsAccount.getUserId());
	  ModelAndView model = new ModelAndView("redirect:/loginMain");
	  return  model;
  }
  
  @RequestMapping(value = "/searchVehicle", method = RequestMethod.GET)
  public ModelAndView searchVehicle(ModelAndView oldmodel) {
	  System.out.println("Made it to searchvehicle handler");
	//  Vehicle vehicle = new Vehicle(Vehicle_Type.values()[type], year, value);
	  List<Vehicle> vlist = searchVehicle(rsAccount.getUserId());
	  ModelAndView model = new ModelAndView("searchVehicles");
	  model.addObject("searchList", vlist);
	  //model.setViewName("searchVehicles");
      return model;
  }
  
  @RequestMapping(value = "/swapVehicle", method = RequestMethod.GET)
  public ModelAndView swapVehicle(HttpServletRequest request) {

      int vehicleId = Integer.parseInt(request.getParameter("id"));
	  System.out.println("Made it to swapvehicle handler with targ Id" + vehicleId);
      targVehicle = rsaccountDAO.getVehicle(vehicleId);
  	  List<Vehicle> vlist = rsaccountDAO.getVehicles(rsAccount.getUserId());
  	  Vehicle tempVehicle = new Vehicle();
  	  ModelAndView model = new ModelAndView("selectSwapVehicle");
	  model.addObject("selectVehicle", tempVehicle);
	  model.addObject("vehicleList", vlist);
      return model;
  }
 
  @RequestMapping(value = "/createSwap", method = RequestMethod.GET)
  public ModelAndView createSwap(HttpServletRequest request) {

	      int vehicleId = Integer.parseInt(request.getParameter("id"));
	      System.out.println("Made it to createswap handler lookin for vehicleId" + vehicleId);
	      initVehicle = rsaccountDAO.getVehicle(vehicleId);
	      //Create swap request here
	      createSwapRequest(initVehicle.getVehicleId(), targVehicle.getVehicleId());
	 	  ModelAndView model = new ModelAndView("redirect:/loginMain");
	 	  return model;
	     
  }
  
  @RequestMapping(value = "/deleteSwapReq", method = RequestMethod.GET)
  public ModelAndView deleteSwapReq(HttpServletRequest request) {
	  System.out.println("Made it to delSwapReq handler");
	  int swapReqId = Integer.parseInt(request.getParameter("id"));
	  rsaccountDAO.deleteSwapReq(swapReqId);
	  ModelAndView model = new ModelAndView("redirect:/loginMain");
	  return  model;
  }
  
  @RequestMapping(value = "/acceptSwapReq", method = RequestMethod.GET)
  public ModelAndView acceptSwapReq(HttpServletRequest request) {
	  System.out.println("Made it to accSwapReq handler");
	  int swapReqId = Integer.parseInt(request.getParameter("id"));
	  rsaccountDAO.updateSwapReq(swapReqId, "ACCEPTED");
	  ModelAndView model = new ModelAndView("redirect:/loginMain");
	  return  model;
  }
   
  @RequestMapping(value = "/declineSwapReq", method = RequestMethod.GET)
  public ModelAndView declineSwapReq(HttpServletRequest request) {
	  System.out.println("Made it to decSwapReq handler");
	  int swapReqId = Integer.parseInt(request.getParameter("id"));
	  rsaccountDAO.updateSwapReq(swapReqId, "DECLINED");
	  ModelAndView model = new ModelAndView("redirect:/loginMain");
	  return  model;
  }
   
  public void addVehicle(String type, String year, String value)
  {
	  Vehicle vehicle = new Vehicle(type, year, value);
	  rsaccountDAO.insertVehicle(vehicle, rsAccount.getUserId());
	  
	  // Update view here
  }
  
  public void removeVehicle(String type, String year, String value)
  {
	  Vehicle vehicle = new Vehicle(type, year, value);
	  rsaccountDAO.removeVehicle(vehicle, rsAccount.getUserId());
	  
	  // Update view here
  }
  
  public ArrayList<Vehicle> searchVehicle(int userId)
  {
	  ArrayList<Vehicle> vlist = rsaccountDAO.searchVehicles(userId);
	  
	  return vlist;
  }
  
  public void createSwapRequest(int initVehicleId, int targVehicleId)
  {
	  System.out.println("Made it to createswap request");
	  //Vehicle temp = rsaccountDAO.getVehicle(targVehicleId);
	  rsaccountDAO.insertSwapReq(initVehicleId, targVehicleId, rsAccount.getUserId(), targVehicle.getUserId());
  }
  
  
  public void blockUser(int userId)
  {
      rsaccountDAO.updateAccountBlock(userId, true);
  }
  
}