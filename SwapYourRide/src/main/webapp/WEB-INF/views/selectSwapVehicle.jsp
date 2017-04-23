<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Swap Your Ride</title>
</head>
<body>
 <h2>My Vehicles:</h2>
 <h3>Select a vehicle to swap:</h3>
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
		  <td><a href="createSwap?id=${vehicle.vehicleId}">Swap</a></td>
	    </tr>
	  </c:forEach>	
	</table>  
</body>
</html>