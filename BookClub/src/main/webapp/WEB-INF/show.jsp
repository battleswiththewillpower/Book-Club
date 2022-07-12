<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="/books">Back to the shelves</a>

<h1> <c:out value="${book.title}" /></h1>

<c:out value="${user.name}" /> read <c:out value="${book.title}"/> by <c:out value="${book.name}"/>.
Here are <c:out value="${user.name}"/> thoughts: 
<p>"<c:out value="${book.thoughts}"/>."</p>

<c:choose>
<c:when test="${user_id == author.user.id}">
<a href="/books/${book.id}/edit">Edit</a>
</c:when>
</c:choose>
</body>
</html>