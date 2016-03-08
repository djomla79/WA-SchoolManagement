<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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