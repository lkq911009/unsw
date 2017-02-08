<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${results[i].title}"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="sellermenu.html"%>
<section class="container">
<h2>Entry:</h2>

<img src="${results[i].picture}" width = "200"/>
<p>
<form action='itemonsaleresult' method='GET'>
<Button TYPE='submit' name='index' VALUE='${index}'>Back</button>
</form>
<p>
<c:choose>
			<c:when test = "${results[i].paused eq true}">
			<form action = "dispatcher?operation=pause&i=${i}&from=info" method = "POST">
			 <input type = "submit" name = "action" value ="Resume"></form>
			</c:when>
			 <c:otherwise>
			 <form action = "dispatcher?operation=pause&i=${i}&from=info" method = "POST">
			 <input type = "submit" name = "action" value ="Pause"></form>
			 </c:otherwise>
		</c:choose>
<table id = "info">
<tr><td>Seller: </td><td><c:out value="${results[i].seller}"/></td></tr>
<tr><td>Title : </td><td><c:out value="${results[i].title}"/></td></tr>
<tr><td>Type : </td><td><c:out value="${results[i].type}"/></td></tr>
<tr><td>Authors : </td>
<td><c:out value="${results[i].authors}"/></td></tr>
<tr><td>Price : </td>
<td><c:out value="${results[i].cost}"/></td></tr>
<tr><td>Editors : </td>
<td><c:out value="${results[i].editors}"/></td></tr>
<tr><td>Book Title : </td><td><c:out value="${results[i].bookTitle}"/></td></tr>
<tr><td>Pages : </td><td><c:out value="${results[i].pages}"/></td></tr>
<tr><td>Year : </td><td><c:out value="${results[i].year}"/></td></tr>
<tr><td>Publisher : </td><td><c:out value="${results[i].publisher}"/></td></tr>
<tr><td>Journal : </td><td><c:out value="${results[i].journal}"/></td></tr>
<tr><td>Volume : </td><td><c:out value="${results[i].volume}"/></td></tr>
<tr><td>Number : </td><td><c:out value="${results[i].number}"/></td></tr>
<tr><td>Month : </td><td><c:out value="${results[i].month}"/></td></tr>
<tr><td>ISBN : </td><td><c:out value="${results[i].ISBN}"/></td></tr>

</table>
</section>
</body>
</html>