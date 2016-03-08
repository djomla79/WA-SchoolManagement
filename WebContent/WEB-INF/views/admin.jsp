<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Administrator Page</title>
</head>
<body>
    
<%--     <jsp:include page="/WEB-INF/views/fragments/navbar.jsp" /> --%>
		 
		<h3>Welcome Administrator <c:out value="${admin.name}"/> <c:out value="${admin.lastName}"/></h3>
	
		<p><a href="<spring:url value="/registerUser"/>">User Registration</a></p>
		<p><a href="<spring:url value="/addingSubject"/>">Add a new Subject</a></p>
		<p><a href="<spring:url value="/addSubjectToProf"/>">Add a Subject to Professor</a></p>
		<p><a href="<spring:url value="/logout"/>">Log out</a></p>

		<h4>Professors</h4>
		
		<c:forEach items="${professors}" var="prof">
			<p> <c:out value="${prof.name}" /> <c:out value="${prof.lastName}" /> 
				<c:url value="/getProfessorWithSubjects/${prof.id}" var="url"/>
				<a href="${url}">Professor info</a></p>
		</c:forEach>
		
		<h4>Requests</h4>
		
		<c:forEach items="${subjectRequests}" var="subjectRequest">
			<p><c:out value="${subjectRequest.studentId}"></c:out>
			<c:url value="/getStudentWithSubjects/${subjectRequest.studentId}" var="url"/>
			<a href="${url}">Student's info</a></p>
			<p><c:url value="/getSubject/${subjectRequest.subjectId}" var="url"/>
			<a href="${url}">Subject's info</a></p>
			<p><c:url value="/acceptSubjectRequest/${subjectRequest.id}" var="url"/>
			<a href="${url}">Accept request</a></p>
			<p><c:url value="/declineSubjectRequest/${subjectRequest.id}" var="url"/>
			<a href="${url}">Decline request</a></p>
		</c:forEach>

</body>
</html>