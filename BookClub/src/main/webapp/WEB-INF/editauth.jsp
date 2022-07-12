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
<h1>Change your Entry</h1>
<a href="/books">Back to the shelves</a>
 <form:form action="/books/${editAuthor.id}/edit" method="post" modelAttribute="editAuthor">
  <input type="hidden" name="_method" value="put">
    <div>
		<form:label path="title"> title: </form:label><br />
		<form:input path="title" value="${ editAuthor.title }" />
		<form:errors path="title"/>
	</div>
	<div>
		<form:label path="name"> Author: </form:label><br />
		<form:input path="name" value="${ editAuthor.name }" />
		<form:errors path="name"/>
	</div>
	<div>
		<form:label path="thoughts">Thoughts: </form:label><br />
		<form:textarea rows="3" path="thoughts" value="${ editAuthor.thoughts }"/>
		<form:errors path="thoughts" />
	</div>

	
    
    <input type="submit" value="Update"/>
</form:form> 

</body>
</html>