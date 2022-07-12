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
<a href="/books">Back to the shelves</a>
<h1>New Author</h1>
 <form:form action="/author/new" method="post" modelAttribute="newAuthor">
    <div>
		<form:label path="title"> title: </form:label><br />
		<form:input path="title"/>
		<form:errors path="title"/>
	</div>
	<div>
		<form:label path="name"> Author: </form:label><br />
		<form:input path="name"/>
		<form:errors path="name"/>
	</div>
	<div>
		<form:label path="thoughts">Thoughts: </form:label><br />
		<form:textarea rows="3" path="thoughts"/>
		<form:errors path="thoughts" />
	</div>

	
    
    <input type="submit" value="Submit"/>
</form:form> 

</body>
</html>