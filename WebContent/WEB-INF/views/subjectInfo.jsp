<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Subject Page</title>
</head>
<body>
	
	<h3>Subject: <c:out value="${subject.subjectName}" /></h3>
		
	<h3>Professor for this subject: <c:out value="${professor.name}" /> <c:out value="${professor.lastName}" /></h3>
	
</body>
</html>