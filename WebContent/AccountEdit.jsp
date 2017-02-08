<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AccountEdit</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<c:choose>
		<c:when test="${from == 'user'}">
			<%@ include file="menu.html"%>
		</c:when>
		<c:otherwise>
			<%@ include file="sellermenu.html"%>
		</c:otherwise>
	</c:choose>

<section class="container">
<h3>Information Edit</h3>

<form action = "dispatcher?operation=infoedit" METHOD = "POST">
<table>
<tr><td>Password: *</td><td><input type="password" name = "password" maxlength="16" placeholder="******"></td></tr>
<tr><td>First name: *</td><td><input type="text" name ="first_name" value = "${first_name}" placeholder="${user.getfName()}"></td></tr>
<tr><td>Last name: *</td><td><input type="text" name ="last_name" value = "${last_name}" placeholder="${user.getlName()}"></td></tr>
<tr><td>Nick name: </td><td><input type="text" name ="nick_name" value = "${nick_name}" placeholder="${user.getNickname()}"></td></tr>
<tr><td>DOB: *</td><td><input type="text" name ="dob" value="${dob}" placeholder="${user.getDOB()}"></td></tr>
<tr><td>Street number: *</td><td><input type="text" name ="st_no" value="${st_no}" placeholder="${user.getStNo()}"></td></tr>
<tr><td>Street name: *</td><td><input type="text" name ="st_address" value="${st_address}" placeholder="${user.getStreet()}"></td></tr>
<tr><td>Suburb: *</td><td><input type="text" name ="suburb" value="${suburb}" placeholder="${user.getSuburb()}"></td></tr>
<tr><td>Postcode: *</td><td><input type="text" name ="postcode" value="${postcode}" maxlength="4" placeholder="${user.getPostcode()}"></td></tr>
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
<tr><td>Credit Card number: *</td><td><input type="text" name ="credit_card" placeholder="${user.getCreditCard()}"></td></tr>
</select></td></tr>
</table>

<Td><input type="submit" name="action" value="Submit"></Td>
<td><button onclick = "history.go(${back})">Back</button></td>
</tr></table>

</section>

</body>
</html>