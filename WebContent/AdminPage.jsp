<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AdminPage</title>
</head>
<body>
<link rel="stylesheet" href="css/style.css">
<%@ include file="AdminHeader.html"%>


<section class="container">
<div class="search">
<h1>Search for books</h1>
<form action = "searchresults" METHOD = "POST">
<div id = "search_engine">
<p id = "selection"><select name="inputType"><option value="title">Title</option><option value="author">Author</option>
<option value="Editor">Editor</option><option value="Publisher">Publisher</option>
<option value="Journal">Journal</option></select></p>
<input type="text" name ="searchquery">
<p><input type = "submit" name="submit" value="Search" formaction="dispatcher?operation=adminbooksearch">

</form>




<h1>Search for User</h1>
<form action = "searchresults" METHOD = "POST">
<div id = "search_engine">
<input type="text" name ="searchquery">
<p><input type = "submit" name="submit" value="Search" formaction="dispatcher?operation=adminusersearch">

</form></div>
</section>


</body>
</html>