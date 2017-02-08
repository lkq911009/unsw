<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.*, java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value="${book.title}"/></title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@ include file="AdminHeader.html"%>
<section class="container">
<h2>Entry:</h2>
${message}
<img src="${book.picture}" width = "200"/>
<p>
<button onclick = "history.go(${back})">Back</button></p>
<form action = "dispatcher?operation=deleteentry&i=${book.ID}" method = "POST">
<input type = "submit" name = "action" value ="Delete Item"></form></p>
<p>

<table id = "info">
<tr><td>Seller: </td><td><c:out value="${book.seller}"/></td></tr>
<tr><td>Title : </td><td><c:out value="${book.title}"/></td></tr>
<tr><td>Type : </td><td><c:out value="${book.type}"/></td></tr>
<tr><td>Authors : </td>
<td><c:out value="${book.authors}"/></td></tr>
<tr><td>Price : </td>
<td><c:out value="${book.cost}"/></td></tr>
<tr><td>Editors : </td>
<td><c:out value="${book.editors}"/></td></tr>
<tr><td>Book Title : </td><td><c:out value="${book.bookTitle}"/></td></tr>
<tr><td>Pages : </td><td><c:out value="${book.pages}"/></td></tr>
<tr><td>Year : </td><td><c:out value="${book.year}"/></td></tr>
<tr><td>Publisher : </td><td><c:out value="${book.publisher}"/></td></tr>
<tr><td>Journal : </td><td><c:out value="${book.journal}"/></td></tr>
<tr><td>Volume : </td><td><c:out value="${book.volume}"/></td></tr>
<tr><td>Number : </td><td><c:out value="${book.number}"/></td></tr>
<tr><td>Month : </td><td><c:out value="${book.month}"/></td></tr>
<tr><td>ISBN : </td><td><c:out value="${book.ISBN}"/></td></tr>

</table>
</section>
</body>
</html>