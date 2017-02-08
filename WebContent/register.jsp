<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<section class="container">
<h3>Registration</h3>
<p>${message}</p>
<h3>* indicates mandatory field</h3>
<form action = "dispatcher?operation=register" METHOD = "POST">
<table>
<tr><td>Username: *</td><td><input type="text" name ="username" value ="${username}" maxlength="16" required></td></tr>
<tr><td>Password: *</td><td><input type="password" name = "password" maxlength="16" required></td></tr>
<tr><td>Email: *</td><td><input type="text" name ="email" value = "${email}" required></td></tr>
<tr><td>First name: *</td><td><input type="text" name ="first_name" value = "${first_name}" required></td></tr>
<tr><td>Last name: *</td><td><input type="text" name ="last_name" value = "${last_name}" rquired></td></tr>
<tr><td>Nick name: </td><td><input type="text" name ="nick_name" value = "${nick_name}" ></td></tr>
<tr><td>DOB (yyyy-mm-dd): *</td><td><input type="text" name ="dob" value="${dob}" required></td></tr>
<tr><td>Street number: *</td><td><input type="text" name ="st_no" value="${st_no}" required></td></tr>
<tr><td>Street name: *</td><td><input type="text" name ="st_address" value="${st_address}" required></td></tr>
<tr><td>Suburb: *</td><td><input type="text" name ="suburb" value="${suburb}" required></td></tr>
<tr><td>Postcode: *</td><td><input type="text" name ="postcode" value="${postcode}" maxlength="4" required></td></tr>
<tr><td>State: *</td><td id = "selection"><select name="state">
<option value="NSW">NSW</option>
<option value="VIC">VIC</option>
<option value="WA">WA</option>
<option value="NT">NT</option>
<option value="TAS">TAS</option>
<option value="QLD">QLD</option>
<option value="SA">SA</option>
<option value="ACT">ACT</option>
</select></td></tr>
<tr><td>Credit Card number: *</td><td><input type="text" name ="credit_card" maxlength="16" required></td></tr>
<tr><td>Account type: *</td><td id = "selection"><select name="account_type"><option value="customer">Customer</option><option value="seller">Seller</option>
</select></td></tr>
</table>
<table><tr><td><input type="reset"  value="Reset"></td>
<Td><input type="submit" name="action" value="Submit"></Td>
<td><button onclick = "history.go(${back})">Back</button></td>
</tr></table>

</section>

</body>
</html>