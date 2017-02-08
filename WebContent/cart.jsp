<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Your Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="menu.html"%>
<section class="container">
<h2>Your Shopping Cart:</h2>
<c:choose>
    <c:when test="${fn:length(cart) eq 0}">
       <p>Shopping cart is empty.<br><button onclick = "history.go(${back})">Back</button></p>
    </c:when>
    <c:otherwise>
        <form action="dispatcher?operation=cart" method="POST">
		<p><table>
		<c:forEach var="i" begin="0" end="${fn:length(cart)-1}">
			<tr><td><input type="checkbox" name="${i}" value="remove"></td>
			<td><a href='dispatcher?operation=entryinfo&i=${i}&from=cart'><c:out value="${cart[i].title}"/></a></td>
			</c:forEach>
		</table></p>
		<input type="submit" name="action" value = "Remove"/>
		<input type="submit" name="action" value="Purchase"/>
		</form> 
		<button onclick = "history.go(${back})">Back</button>
	</c:otherwise>
</c:choose>
</section>
</body>
</html>