<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.*"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="AdminHeader.html"%>
<section class="container">
<c:choose>
    <c:when test="${index+9 gt fn:length(results)-1}">
       <c:set var="max" value="${fn:length(results)-1}"/>
    </c:when>
    <c:otherwise>
        <c:set var="max" value="${index+9}"/>
    </c:otherwise>
</c:choose>
<c:choose>
	<c:when test = "${max lt 0}">
	<p>There were no item!</p>
	</c:when>
	<c:otherwise>
	<h1>
	<fmt:parseNumber integerOnly="true"  var = "current_page" value =  "${index / 10 +1}"/>
	<fmt:parseNumber integerOnly="true"  var = "last_page" value = "${(fn:length(results)-1)/10 + 1}"/>
	Displaying page ${current_page} of ${last_page} pages
	</h1>
	<table id ="list">
		<tr>
		<th>Image</th>
		<th>Title</th>
		<th>Authors</th>
		<th>Cost</th>
		<th></th>
		
		
		</tr>
		<c:forEach var="i" begin="${index}" end="${max}">
		<tr id ="highlight"><td><img src = "${results[i].picture}" width = "80"></td>
		<td><a href="dispatcher?operation=adminentryinfo&i=${results[i].ID}"><c:out value="${results[i].title}"/></a></td>
		<td width="20%"><c:out value="${results[i].authors}"/></td>
		<td width="10%"><c:out value="${results[i].cost}"/></td>
		<td>
		<c:choose>
			<c:when test = "${addedItem eq i}">
			${message}
			</c:when>
			 <c:otherwise>
			 <form action = "dispatcher?operation=deleteentry&i=${results[i].ID}" method = "POST">
			 <input type = "submit" name = "action" value ="Delete Item"></form>
			 </c:otherwise>
		</c:choose>
		</td>
		</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>


<form action='adminbooksearchresults' method='GET'>
<c:if test="${index > 9}">
<Button TYPE='submit' name='index' VALUE='${index-10}'>previous</button>
</c:if>
<c:if test="${index+10 lt fn:length(results)}">
<button TYPE='submit' name='index' VALUE='${index+10}'>next</button>
</c:if>
</form>
</section>
</body>
</html>