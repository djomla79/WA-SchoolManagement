<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style1.css" />" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>Home Page</title>
</head>
<body>
	<p>Home</p>
	
<%-- 	<c:if test="${user == null}"> --%>
<%-- 	    <a href="<c:url value="/login" />">Log in</a> --%>
<%-- 	</c:if> --%>
	
	<c:choose>
			<c:when test="${user != null} && ${admin != null}">
				<sec:authorize access="hasAuthority('ADMIN')">
					<spring:url value="/admin?id=${admin.id}" var="url" /><a href="${url}">Your Account Administrator</a>
				</sec:authorize>
			</c:when>
			<c:when test="${user != null} && ${professor != null}">
				<sec:authorize access="hasAuthority('PROFESSOR')">
					<spring:url value="/accountProf?id=${professor.id}" var="url" /><a href="${url}">Your Account Professor</a>
				</sec:authorize>
			</c:when>
			<c:when test="${user != null} && ${student != null}">
				<sec:authorize access="hasAuthority('STUDENT')">
					<spring:url value="/accountStudent?id=${student.id}" var="url" /><a href="${url}">Your Account Student</a>
				</sec:authorize>
			</c:when>
			<c:otherwise>
				<h4>Welcome!</h4>
				<p><a href="<c:url value="/login" />">Log in</a></p>
			</c:otherwise>
	</c:choose>
		
	<c:if test="${user != null}">
	    <a href="<c:url value="/logout" />">Log out</a>
	</c:if>
	
</body>
</html>