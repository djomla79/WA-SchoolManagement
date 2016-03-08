<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Subject</title>
</head>
<body>
	
	<p>Add Subject</p>
		<spring:url value="/addSubject" var="formUrl" />
	    <form:form modelAttribute="subject" action="${formUrl}" method="POST">
			
			<label>Input a Subject's Name:</label><br>
			<form:input type="text" path="subjectName" name="subjectName" />
			<input type="submit" />
				
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" >
				
		</form:form>

</body>
</html>