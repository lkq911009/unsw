<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Recover</title>
</head>
<body>
<link rel="stylesheet" href="css/style.css">
<h1>
Account Recovery
</h1>
<form method="post" action="dispatcher?operation=recover">
<table>
<tr><td>Please enter your Username:</td>
<td><input type="text" name="username"></td></tr>

</table>

<table><tr><td><input type="reset" value="Reset"></td>
<Td><input type="submit" name="action" value="Submit"></Td>
<Td><input type="submit" name="action" value="Go To Log In"></Td>
</tr>
</table>

</body>
</html>