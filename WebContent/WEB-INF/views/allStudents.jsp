<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style1.css" />" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>All Students</title>
</head>
<body>
	
	<h1>Students</h1>
	<c:forEach items="${students}" var="student">
		<p><c:out value="${student.name}" /> <c:out value="${student.lastName}" />
		<c:url value="/getStudentWithSubjects/${student.id}" var="url"/>
		<a href="${url}">Student info</a></p>
		<p><c:url value="/getStudentWithSubjectsAndGrades/${student.id}" var="url"/>
		<a href="${url}">Student Subjects and Grades</a></p>
	</c:forEach>
</body>
</html>