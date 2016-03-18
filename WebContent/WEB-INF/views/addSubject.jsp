<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<spring:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Add Subject</title>
</head>
<body>
	
	<jsp:include page="../fragments/admin-navbar.jsp" />
	
	<br><br>
    <div class="well"><h3>Add new Subject</h3></div>
	
    <div class="container">
      <div class="container1">
	
		<spring:url value="/addSubject" var="formUrl" />
	    <form:form modelAttribute="subject" action="${formUrl}" method="POST">
			
			<label>Input a Subject's Name:</label><br>
			<form:input type="text" path="subjectName" name="subjectName" /><br>
			<input type="submit" value="Submit" class="btn btn-sm btn-primary"/>
				
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
				
		</form:form>
		
		</div>
	</div>
</body>
</html>