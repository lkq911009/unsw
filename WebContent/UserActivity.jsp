<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ManageSingleUser</title>
</head>
<link rel="stylesheet" href="css/style.css">
<body>
<%@ include file="AdminHeader.html"%>
<section class="container">
<h2>User Activity</h2>

<c:set var="max" value="${fn:length(carthistory)-1}"/>
<c:choose>
<c:when test = "${max lt 0}">
<p>The User do not have any Cart Activity</p>
</c:when>
<c:otherwise>
<table id ="list">
<tr>
<th>Customer</th>
<th>Title</th>
<th>Added Time</th>
<th>Removed Time</th>
</tr>
<c:forEach var="i" begin="0" end="${fn:length(carthistory)-1}">
<tr id ="highlight"><td><c:out value="${carthistory[i].GetCustomer()}"/></td>
<td><c:out value="${carthistory[i].GetTitle()}"/></td>
<td><c:out value="${carthistory[i].GetAddedTime()}"/></td>
<td><c:out value="${carthistory[i].GetRemovedTime()}"/></td>
</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>

<h2>User Order Activity</h2>

<c:set var="max" value="${fn:length(order)-1}"/>
<c:choose>
<c:when test = "${max lt 0}">
<p>The User do not have any Order Activity</p>
</c:when>
<c:otherwise>
<table id ="list">
<tr>
<th>Customer</th>
<th>Title</th>
<th>Price</th>
<th>Seller</th>
<th>Sale Time</th>
</tr>
<c:forEach var="i" begin="0" end="${fn:length(order)-1}">
<tr id ="highlight"><td><c:out value="${order[i].GetCustomer()}"/></td>
<td><c:out value="${order[i].GetTitle()}"/></td>
<td><c:out value="${order[i].GetPrice()}"/></td>
<td><c:out value="${order[i].GetSeller()}"/></td>
<td><c:out value="${order[i].GetSaleTime()}"/></td>
</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>



</section>
</body>
</html>