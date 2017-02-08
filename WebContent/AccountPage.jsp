<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${user.getUsername()}" /></title>
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
	<h2>
		<c:out value="${user.type} ${user.username}" />
	</h2>

	<p>
		<button onclick="history.go(${back})">Back</button>
	</p>
	<p>
	<table id="info">
		<tr>
			<td>Email :</td>
			<td><c:out value="${user.email}" /></td>
		</tr>
		<tr>
			<td>Nickname :</td>
			<td><c:out value="${user.nickname}" /></td>
		</tr>
		<tr>
			<td>First Name :</td>
			<td><c:out value="${user.fName}" /></td>
		</tr>
		<tr>
			<td>Last Name :</td>
			<td><c:out value="${user.lName}" /></td>
		</tr>
		<tr>
			<td>Date of Birth :</td>
			<td><c:out value="${user.DOB}" /></td>
		</tr>
		<tr>
			<td>Address :</td>
			<td><c:out
					value="${user.stNo} ${user.street } ${user.suburb } ${user.postcode } ${user.state }" /></td>
		</tr>
		<tr>
			<td>CreditCard :</td>
			<td><c:out value="${user.creditCard}" /></td>
		</tr>

	</table>




	<c:if test="${from == 'user'}">


		<c:choose>
			<c:when test="${index+9 gt fn:length(orders)-1}">
				<c:set var="max" value="${fn:length(orders)-1}" />

			</c:when>
			<c:otherwise>
				<c:set var="max" value="${index+9}" />

			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${max lt 0}">
				<p>Sorry, you currently have no Orders.</p>
			</c:when>
			<c:otherwise>
				<table id="list">
					<tr>
						<th>Title</th>
						<th>Purchase Time</th>
						<th>Seller</th>
						<th>Price</th>


					</tr>
					<c:forEach var="i" begin="${index}" end="${max}">
						<tr id="highlight">
							<td width="20%"><c:out value="${orders[i].GetTitle()}" /></td>
							<td width="20%"><c:out value="${orders[i].GetSaleTime()}" /></td>
							<td width="20%"><c:out value="${orders[i].GetSeller()}" /></td>
							<td width="20%"><c:out value="${orders[i].GetPrice()}" /></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</c:if>

	<form action="dispatcher?operation=goedit" method="POST">
		<input type="submit" name="action" value="EDIT">
	</form>

	</section>


</body>
</html>