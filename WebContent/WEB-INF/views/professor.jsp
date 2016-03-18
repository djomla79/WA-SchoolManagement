<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Professor</title>
</head>
<body>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
    <div class="well">
         <h3>Professor <c:out value="${professor.name}" /> <c:out value="${professor.lastName}" /></h3>
    </div>
	<div class="well">
		<h5>Username: <c:out value="${professor.username}" />, Password: <c:out value="${professor.password}" /></h5>
	</div>
	
	<div class="container">
    	<div class="header1"><h4>Professor Subjects</h4></div>
			<c:forEach items="${subjects}" var="subject">		
				<c:out value="${subject.subjectName}" /><br>
			</c:forEach>
	</div>
	
</body>
</html>