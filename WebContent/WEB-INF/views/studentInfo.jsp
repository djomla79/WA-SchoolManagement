<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Student Page</title>
</head>
<body>
	
	<h3>Student <c:out value="${student.name}" /> <c:out value="${student.lastName}" /></h3>
		
		<p>Username: <c:out value="${student.username}" /></p>
		<p>Password: <c:out value="${student.password}" /></p>
		
	<h4>Student Subjects</h4>
	<c:forEach items="${subjects}" var="subject">		
		<c:out value="${subject.subjectName}" />
		<br>
	</c:forEach>
	
</body>
</html>