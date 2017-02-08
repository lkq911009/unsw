<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>administration login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<section class="container">
	<div class="login">
		<h1>Admin Login</h1>
		<p id="error">${message}<br></p>
		<form method="post" action="dispatcher?operation=adminlogin">
			<p>
				<input type="text" name="adminname" value=""
					placeholder="Adminname">
			</p>
			<p>
				<input type="password" name="password" value=""
					placeholder="Password">
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="login">
			</p>
		</form>
	</div>


	</section>

</body>
</html>