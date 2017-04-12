<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Menu</title>
</head>
<body>
<h1> Main Menu</h1>
Welcome ${sessionScope.userId}! 
<a href="Logout"><button>Logout</button></a></br>
</br>
<c:forEach items="${Menus}" var="menu" varStatus="loop">
<a href="ViewMenu?id=${menu.id}"><button>${menu.name}</button></a> </br></br>
		


	</c:forEach>
	
	

</body>
</html>