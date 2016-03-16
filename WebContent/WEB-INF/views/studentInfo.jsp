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
	
	<jsp:include page="/WEB-INF/views/fragments/navbar.jsp" />
	
	<h4>Student <c:out value="${student.name}" /> <c:out value="${student.lastName}" /></h4>
		
		<p>Username: <c:out value="${student.username}" /> Password: <c:out value="${student.password}" /></p>
		
	<h5>Subject: <c:out value="${subject.subjectName}" /></h5>
	<h5>Grades: </h5>
	<c:forEach items="${grades}" var="grade">		
		<c:out value="${grade.gradeValue}" />, 
	</c:forEach>
	
	<h5>Average grades for this subject: <c:out value="${subjectAverage}" /></h5>
	
	<h5>Absences for this subject: <c:out value="${absences}" /></h5>
	
</body>
</html>