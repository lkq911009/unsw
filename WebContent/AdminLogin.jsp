<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" href="css/style.css">
<section class = "menu">
<table><tr><td>
<div id = "logo">Bookology Admin Management System</div>
<div id = "small">The world's most legitimate online bookstore!</div></td>
<td>

</td></tr></table>

</section>

<section class="container">
	<div class="login">
		<h1>Login to Admin Page</h1>
		<p id="error">${message}<br></p>
		<form method="post" action="dispatcher?operation=adminlogin">
			<p>
				<input type="text" name="adminname" value=""
					placeholder="Username">
			</p>
			<p>
				<input type="password" name="password" value=""
					placeholder="Password">
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="Login">
				<input type="reset" value="Reset">
			</p>
		</form>
	</div>

</body>
</html>