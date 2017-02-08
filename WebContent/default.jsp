<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<section class = "menu">
<table><tr><td>
<div id = "logo">Bookology</div>
<div id = "small">The world's most legitimate online bookstore!</div></td>
<td>

</td></tr></table>

</section>
	
	
	<section class="container">
	<div class="login">
		<h1>Login to Book Store</h1>
		<p id="error">${message}<br></p>
		<form method="post" action="dispatcher?operation=login">
			<p>
				<input type="text" name="username" value=""
					placeholder="Username">
			</p>
			<p>
				<input type="password" name="password" value=""
					placeholder="Password">
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="Login">
				<input type="submit" name="commit" value="Register" formaction="dispatcher?operation=register">
			</p>
		</form>
	</div>

	<div class="login-help">
		<p>
			Forgot your password? <a href="AccountRecover.jsp">Click here to reset it</a>.
		</p>
	</div>
	</section>


</body>
</html>