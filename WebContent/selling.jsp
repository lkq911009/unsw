<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Item For Sale</title>
</head>
<link rel="stylesheet" href="css/style.css">
<body>
<%@ include file="sellermenu.html"%>
<section class="container">
<p id="error">${message}<br></p>
<form method="post" action="dispatcher?operation=addingentry">
<table>
  <tr><td>Title:</td>
  <td><input type="text" name="title"></td></tr>
  
  <tr><td>Authors:</td>
  <td><input type="text" name="author"></td></tr>
 
  <tr><td>Editors:</td>
  <td><input type="text" name="editor"></td></tr>
  
  <tr><td>Type:</td>
  <td><select name="type">
  <option value="PROCEEDINGS">PROCEEDINGS</option>
  <option value="INPROCEEDINGS">INPROCEEDINGS</option>
  <option value="ARTICLE">ARTICLE</option>
  <option value="BOOK">BOOK</option>
  <option value="INCOLLECTION">INCOLLECTION</option>
  </select></td></tr>
  
  <tr><td>Book Title:</td>
  <td><input type="text" name="bookTitle"></td></tr>
  
  <tr><td>Pages:</td>
  <td><input type="text" name="page"></td></tr>
  
  <tr><td>Year:</td>
  <td><select id="year" name="year">
  <script>
  var myDate = new Date();
  var year = myDate.getFullYear();
  for(var i = 1900; i < year+1; i++){
	  document.write('<option value="'+i+'">'+i+'</option>');
  }
  </script>
  </select>
  </td></tr>
  
  <tr><td>Publisher:</td>
  <td><input type="text" name="publisher"></td></tr>
  
  <tr><td>Journal:</td>
  <td><input type="text" name="journal"></td></tr>
  
  <tr><td>Volume:</td>
  <td><input type="text" name="volume"></td></tr>
  
  <tr><td>Number:</td>
  <td><input type="text" name="number"></td></tr>
  
  <tr><td>Month:</td>
  <td><input type="text" name="month"></td></tr>
  
  <tr><td>ISBN:</td>
  <td><input type="text" name="ISBN"></td></tr>
  
  <tr><td>Cost:</td>
  <td><input type="text" name="cost"></td></tr>
  
  <tr><td>Link to Cover Photo:</td>
  <td><input type="text" name="picture"></td></tr>
  
  
</form>
</table>

<table><tr><td><input type="submit" name="action" value="Reset"></td>
<Td><input type="submit" name="action" value="Submit"></Td></tr>
</table>

</section>

</body>
</html>