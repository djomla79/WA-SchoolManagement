<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style1.css" />" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Professor</title>
</head>
<body>
	
	<h3>Professor <c:out value="${professor.name}" /> <c:out value="${professor.lastName}" /></h3>
		
		<p>Username: <c:out value="${professor.username}" /></p>
		<p>Password: <c:out value="${professor.password}" /></p>
		
	<h4>Professor Subjects</h4>
	<c:forEach items="${subjects}" var="subject">		
		<c:out value="${subject.subjectName}" />
		<br>
	</c:forEach>
	
</body>
</html>