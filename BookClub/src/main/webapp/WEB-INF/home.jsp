<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome <c:out value="${user.name}" /></h1>

<a href="/author/new">Add a to my shelf!</a>
<table>
  <tr>
    <th>ID</th>
    <th>Title</th>
    <th>Author Name</th> 
    <th>Posted By</th>
  </tr>

<c:forEach  var="author" items="${ authors }">
  <tr>
     
    <td><c:out value="${author.id}"></c:out> </td> 
	<td><a href="/books/${author.id}"><c:out value="${author.title}"></c:out></a></td>
	<td><c:out value="${author.name}"></c:out></td>
	<!-- fix this -->
	<td><c:out value="${author.user.name}"></c:out></td>
	
	<td> 
	</td>
  </tr>
 </c:forEach>
</table>

<a href="/logout">Logout</a>

</body>
</html>