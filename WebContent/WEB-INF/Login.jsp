<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login</title>
</head>
<body>
<form action="Login" method="post">
<div class="top">
	  <h1>Login</h1></div>
	  <c:if test="${not empty message}">
   			<h3><strong>${message}</strong></h3>   
		</c:if>
    	<div>
				<Strong><label for="username">Username:</label>
				<input type="text" id="username" name="username" required>
		</div>
		</br>
		<div>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required></Strong>
		</div>
		</br>
		<div>
			<button type="submit">Login</button>
		</div>
	</form>
</body>
</html>