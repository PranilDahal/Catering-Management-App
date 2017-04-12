<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Dish</title>
</head>
<body class ="editBody">

	<form action="AddDish?id=${menuId}" method="post">
		<h1>Add New Dish</h1>
		<fieldset>
			<legend>About Dish</legend>
			<div>
				<label for="dishname">Dish Name:</label>
				<input id="dishname" name="dishname" value="" required>
			</div>

		</fieldset>
		
		<fieldset>
			<legend>People Info: </legend>		

			<div>
				<label for="Ings"> Ingredients: (Put 0 for useless ones)</label></br>
				<c:forEach items="${Ings}" var="Ing">					

						
							
						${Ing.name}
						<label for="Amount">->>> Amount:</label>				
				<input type="number" id="Amount" name="Amount"
					min="0" max="50" value="0" required>		</br>		

				</c:forEach>


			</div>

		</fieldset>

		<input type="hidden" name="id">
		<div class="add">
			<button type="submit">Add Dish</button>
		</div>
	</form>

</body>
</html>