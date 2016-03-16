<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Subject Page</title>
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/fragments/navbar.jsp" />
	
	<h4>Subject: <c:out value="${subject.subjectName}" /></h4>
		
	<h4>Professor for this subject: <c:out value="${professor.name}" /> <c:out value="${professor.lastName}" /></h4>
	
</body>
</html>