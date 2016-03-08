<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Professor Account</title>
</head>
<body>
	
	<h1>Welcome Professor <c:out value="${loggedProfessor.name}"/> <c:out value="${loggedProfessor.lastName}"/></h1>
	
	<br>
	<h2>Your Subjects</h2>
	<c:forEach items="${loggedProfessor.subjects}" var="subject">
			<c:out value="${subject.subjectName}"/> <br>
			<c:url value="/getSubjectWithStudents/${subject.id}" var="url"/>
			<a href="${url}">Get students with this subject</a>
			<br>
	</c:forEach>

</body>
</html>