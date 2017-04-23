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
<h2>Other Vehicles:</h2>
 <h3>Select a vehicle to swap:</h3>
  <table border="1">
	  <th>No</th>
	  <th>Type</th>
	  <th>Year</th>
	  <th>Value</th>
	  <th>Action</th>
      <c:forEach var="tempv" items="${searchList}" varStatus="status">
	    <tr>
	      <td>${status.index + 1}</td>
		  <td>${tempv.type}</td>
		  <td>${tempv.year}</td>
		  <td>${tempv.value}</td>
		  <td><a href="swapVehicle?id=${tempv.vehicleId}">Swap</a></td>
	    </tr>
	  </c:forEach>	
	</table>  
</body>
</html>