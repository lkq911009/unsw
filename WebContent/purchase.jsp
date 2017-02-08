<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ page import="edu.unsw.comp9321.jdbc.*"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm your order</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="menu.html"%>
<section class="container">
<h2>Confirm your order:</h2>
        <form action="dispatcher?operation=purchase" method="POST">
        <c:set var="total" value="0"/>
		<p><table>
		<tr><th>Title</th><th>Seller</th><th>Price</th></tr>
		<c:forEach var="i" begin="0" end="${fn:length(cart)-1}">
		<c:set var="total" value="${total+cart[i].cost}"/>
		<tr><td><c:out value="${cart[i].title}"/></td><td><c:out value = "${cart[i].seller}"/></td>
		<td><fmt:formatNumber value="${cart[i].cost}" type="currency"/></td></tr>
		</c:forEach>
		<tr><td><strong>Total:<strong></strong></td><td></td><td><fmt:formatNumber value="${total}" type="currency"/></td></tr>
		</table></p>
		<input type="submit" name="action" value="Back to Cart">
		<input type="submit" name="action" value="Confirm">
		</form> 

</section>
</body>
</html>