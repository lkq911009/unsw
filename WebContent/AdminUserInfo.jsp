<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${user.getUsername()}"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="AdminHeader.html"%>
<section class="container">

<p>
<button onclick = "history.go(-1)">Back</button></p>

<p>

<table id = "info">
<tr><td>Username: </td><td><c:out value="${user.getUsername()}"/></td></tr>
<tr><td>DOB : </td><td><c:out value="${user.getDOB()}"/></td></tr>
<tr><td>Email : </td><td><c:out value="${user.getEmail()}"/></td></tr>
<tr><td>Street No : </td><td><c:out value="${user.getStNo()}"/></td></tr>
<tr><td>Street : </td><td><c:out value="${user.getStreet()}"/></td></tr>
<tr><td>Suburb : </td><td><c:out value="${user.getSuburb()}"/></td></tr>
<tr><td>Postcode : </td><td><c:out value="${user.getPostcode()}"/></td></tr>
<tr><td>State : </td><td><c:out value="${user.getState()}"/></td></tr>
<tr><td>CreditCard : </td><td><c:out value="${user.getCreditCard()}"/></td></tr>
</table>


<c:choose>
	<c:when test = "${user.getType() eq 'CUSTOMER'}">
	<p>Cart History</p>
	<c:choose>
		<c:when test = "${fn:length(history)-1 lt 0}">
		<p>This User Currently Have No Cart History.</p>
		</c:when>
		<c:otherwise>
		<table id ="list">
			<tr>
			<th>Title</th>
			<th>Added Time</th>
			<th>Removed Time</th>
			</tr>
			<c:forEach var="i" begin="0" end="${fn:length(history)-1}">
			<tr id ="highlight">
			<td width="20%"><a href='dispatcher?operation=adminentryinfo&i=${history[i].GetTitleID()}'><c:out value="${history[i].GetTitle()}"/></a></td>
			<td width="20%"><c:out value="${history[i].GetAddedTime()}"/></td>
			<td width="20%"><c:out value="${history[i].GetRemovedTime()}"/></td>		
			</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
	<p>Order History</p>
	<c:choose>
		<c:when test = "${fn:length(order)-1 lt 0}">
		<p>This User Currently Have No Order History.</p>
		</c:when>
		<c:otherwise>
		<table id ="list">
			<tr>
			<th>Title</th>
			<th>Sale Time</th>
			<th>Price</th>
			<th>Seller</th>
			
			</tr>
			<c:forEach var="i" begin="0" end="${fn:length(order)-1}">
			<tr id ="highlight">
			<td width="20%"><a href='dispatcher?operation=adminentryinfo&i=${order[i].GetTitleID()}'><c:out value="${order[i].GetTitle()}"/></a></td>
			<td width="20%"><c:out value="${order[i].GetSaleTime()}"/></td>
			<td width="20%"><c:out value="${order[i].GetPrice()}"/></td>
			<td width="20%"><c:out value="${order[i].GetSeller()}"/></td>		
			</tr>
			</c:forEach>
			</table>
		</c:otherwise>
	
	</c:choose>
	</c:when>
	<c:otherwise>
	<p>This is a Seller Account, No Cart or Order Activity allowed</p>
	</c:otherwise>
</c:choose>



<c:choose>
			<c:when test = "${user.getBanned() eq true}">
			<form action = "dispatcher?operation=ban&name=${user.getUsername()}" method = "POST">
			 <input type = "submit" name = "action" value ="Unban"></form>
			</c:when>
			 <c:otherwise>
			 <form action = "dispatcher?operation=ban&name=${user.getUsername()}" method = "POST">
			 <input type = "submit" name = "action" value ="Ban"></form>
			 </c:otherwise>
		</c:choose>
</section>
</body>
</html>