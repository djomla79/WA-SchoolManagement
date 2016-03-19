<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<spring:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>Messages</title>
</head>
<body>

	<jsp:include page="../fragments/home-navbar.jsp" />
	
	<div class="container">
		<div class="header1">
		<div id="messageError">
			<c:out value="${messageError}" />
		</div>
		<div id="messageOk">
			<c:out value="${messageOk}" />
		</div>
		</div>
	</div>

</body>
</html>