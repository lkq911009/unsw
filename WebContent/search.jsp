<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ page import="edu.unsw.comp9321.jdbc.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<%@ include file="menu.html"%>
	<section class="container">
	<div class="search">
		<h1>Search for books!</h1>
		<form action="searchresults" METHOD="POST">
			<div id="search_engine">
				<p id="selection">
					<select name="inputType"><option value="title">Title</option>
						<option value="author">Author</option>
						<option value="Editor">Editor</option>
						<option value="Publisher">Publisher</option>
						<option value="Journal">Journal</option></select>
				</p>
				<input type="text" name="searchquery">
				<p>
					<input type="submit" name="submit" value="Search"
						formaction="dispatcher?operation=basicsearch">
					<button type="button" onclick="advancedSearch()">Advanced
						Search</button>
				</p>
			</div>
		</form>
	</div>
	<script>
		function basicSearch() {
			document.getElementById("search_engine").innerHTML = '<input type="text" name ="searchquery"/>'
					+ '<p id = "selection"><select name="inputType"><option value="title">Title</option><option value="author">Author</option>'
					+ '<option value="Editor">Editor</option><option value="Publisher">Publisher</option>'
					+ '<option value="Journal">Journal</option></select></p>'
					+ '<p><input type = "submit" name="submit" value="search" formaction="dispatcher?operation=basicsearch">'
					+ '<button type="button" onclick="advancedSearch()">Advanced Search</button></p>';
		}
		function advancedSearch() {
			document.getElementById("search_engine").innerHTML = '<table><tr><td>Title : </td><td><input type="text" name="title"/></td></tr>'
					+ '<tr><td>Author : </td><td><INPUT TYPE="text" NAME="author"/></td></tr>'
					+ '<tr><td>Publisher : </td><td><input type="text" name="publisher"/></td></tr>'
					+ '<tr><td>Type: </td><td><input type="checkbox" name="type" value="Books">Book</input>'
					+ '<input type="checkbox" name="type" value="Journals">Journal</input>'
					+ '<input type="checkbox" name="type" value="Conferences">Conference</input></td><td></td></tr>'
					+ '<tr><td></td></tr></table>'
					+ '<INPUT TYPE="submit" name="submit" VALUE="search" formaction="dispatcher?operation=advancedsearch"/>'
					+ '<button type="button" onclick="basicSearch()">Basic Search</button>';
		}
	</script>
	<table id="list">
		<tr>
			<th>Image</th>
			<th>Title</th>
			<th>Authors</th>
			<th>Price</th>
			<th></th>
		</tr>
		<c:forEach var="i" begin="0" end="9">
			<tr id="highlight">
				<td><img src="${results[i].picture}" width="80"></td>
				<td><a href="dispatcher?operation=entryinfo&i=${i}"><c:out
							value="${results[i].title}" /></a></td>
				<td width="20%"><c:out value="${results[i].authors}" /></td>
				<td width="10%"><c:out value="${results[i].cost}" /></td>
				<td><c:choose>
						<c:when test="${addedItem eq i}">
${message}
</c:when>
						<c:otherwise>
							<form action="dispatcher?operation=entryinfo&i=${i}&from=home"
								method="POST">
								<input type="submit" name="action" value="Add to cart">
							</form>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	</section>
</body>
</html>