<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${CurrentMenu.name}</title>
</head>
<body>
<h1>${CurrentMenu.name}</h1>
<h3>${CurrentMenu.description}</h3>
<c:forEach items="${Dishes}" var="dish" varStatus="loop">
<strong>${dish.name}</br></strong>
<c:forEach items="${dish.ingredients}" var="ing" >
${ing.key.name} : ${ing.value} </br>
</c:forEach>

</br>
</br>
	</c:forEach>
<a href="AddDish?id=${CurrentMenu.id}"><button>ADD/CREATE DISH</button></a></br>


<a href="ListMenu"><button>Back To Main</button></a>
</body>
</html>