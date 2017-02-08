<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdminUserSearchResult</title>
</head>
<body>
<link rel="stylesheet" href="css/style.css">
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
	<p>Sorry, you currently have no User.</p>
	</c:when>
	<c:otherwise>
	<table id ="list">
		<tr>
		<th>Username</th>
		<th>Account Type</th>
		<th>Email</th>
		<th>CreditCard</th>
		<th>Ban</th>
		</tr>
		<c:forEach var="i" begin="${index}" end="${max}">
		<tr id ="highlight">
		<td><a href="dispatcher?operation=adminuserinfo&name=${results[i].getUsername()}"><c:out value =  "${results[i].getUsername()}"/></a></td>
		<td><c:out value="${results[i].getType()}"/></td>
		<td width="20%"><c:out value="${results[i].getEmail()}"/></td>
		<td width="20%"><c:out value="${results[i].getCreditCard()}"/></td>

		<c:choose>
		    <c:when test="${results[i].getBanned()}">
            <td width="10%"><c:out value="YES"/></td>
            </c:when>
            <c:otherwise>
            <td width="10%"><c:out value="NO"/></td>
            </c:otherwise>
        </c:choose>
		
		</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>


<form action='adminusersearchresult' method='GET'>
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