<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Swap Your Ride</title>
</head>
    <body>
    	<div align="center">
    	<h1>Swap Your Ride</h1>
    	<h2>Sign in below:</h2>
		<form:form action="loginUser" method="post" modelAttribute="login">
		<table>
			<form:hidden path="id"/>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Login"></td>
			</tr>
		</table>
		</form:form>
	        <h3><a href="newAccount">Join</a></h3>
	        <h2>${loginMsg}</h2>
	  	</div>
    </body>
</html>