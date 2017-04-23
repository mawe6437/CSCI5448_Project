<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SwapYourRide</title>
</head>
<body>
  <div align="center">
  <h1>Account: ${email}</h1>
  </div>
  <div align="left">
  <h2>My Vehicles:</h2>
    <table border="1">
	  <th>Type</th>
	  <th>Year</th>
	  <th>Value</th>
	  <th>Action</th>
      <c:forEach var="vehicle" items="${vehicleList}" varStatus="status">
	    <tr>
		  <td>${vehicle.type}</td>
		  <td>${vehicle.year}</td>
		  <td>${vehicle.value}</td>
		  <td><a href="deleteVehicle?id=${vehicle.vehicleId}">Delete</a></td>
	    </tr>
	  </c:forEach>	
	</table>  
 <h2>My Swap Requests Sent:</h2>
   <table border="1">
	  <th>Id</th>
	  <th>My Vehicle Info</th>
	  <th>Their Vehicle Info</th>
	  <th>Status</th>
	  <th>Action</th>
      <c:forEach var="swapreq" items="${srSentList}" varStatus="status">
	    <tr>
		  <td>${swapreq.swapReqId}</td>
		  <td>${swapreq.initVehicle.type},${swapreq.initVehicle.year},${swapreq.initVehicle.value}</td>
		  <td>${swapreq.targVehicle.type},${swapreq.targVehicle.year},${swapreq.targVehicle.value}</td>
		  <td>${swapreq.state}</td>
		  <td><a href="deleteSwapReq?id=${swapreq.swapReqId}">Delete</a></td>
	    </tr>
	  </c:forEach>	
	</table>  
 <h2>My Swap Requests Received:</h2>
   <table border="1">
	  <th>Id</th>
	  <th>My Vehicle Info</th>
	  <th>Their Vehicle Info</th>
	  <th>Status</th>
	  <th>Action</th>
      <c:forEach var="swapreql" items="${srRecvList}" varStatus="status">
	    <tr>
		  <td>${swapreql.swapReqId}</td>
		  <td>${swapreql.targVehicle.type}, ${swapreql.targVehicle.year}, ${swapreql.targVehicle.value}</td>
		  <td>${swapreql.initVehicle.type}, ${swapreql.initVehicle.year}, ${swapreql.initVehicle.value}</td>
		  <td>${swapreql.state}</td>
		  <td><a href="acceptSwapReq?id=${swapreql.swapReqId}">Accept</a></td>
		  &nbsp;&nbsp;
		  <td><a href="declineSwapReq?id=${swapreql.swapReqId}">Decline</a></td>
	    </tr>
	  </c:forEach>	
	</table>  
 <h2>Add Vehicle:</h2>
 		<form:form action="addVehicle" method="post" modelAttribute="vehicle">
		<table>
			<form:hidden path="vehicleId"/>
			<tr>
				<td>Type:</td>
				<td><form:input path="type" /></td>
			</tr>
			<tr>
				<td>Year:</td>
				<td><form:input path="year" /></td>
			</tr>
			<tr>
				<td>Value:</td>
				<td><form:input path="value" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Add"></td>
			</tr>
		</table>
		</form:form>
  <h2><a href="searchVehicle">Search Vehicles</a></h2>
   <h3><a href="logout">Logout</a></h3>
</div>     	
</body>
</html>