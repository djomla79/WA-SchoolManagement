<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Administrator Page</title>
</head>
<body>
    
    <jsp:include page="../fragments/admin-navbar.jsp" />
	
	<div class="row">
         <div class="well">
         	<h3>Welcome Administrator <c:out value="${admin.name}"/> <c:out value="${admin.lastName}"/></h3>
         </div>
    </div>	 
	
	<div class="row">
       	<div class="well"><h4><a href="<spring:url value="/registerUser"/>">User Registration</a></h4></div>
    	</div>
	<div class="row">
       	<div class="well"><h4><a href="<spring:url value="/addingSubject"/>">Add a new Subject</a></h4></div>
    	</div>
	<div class="row">
       	<div class="well"><h4><a href="<spring:url value="/addSubjectToProf"/>">Add a Subject to Professor</a></h4></div>
    	</div>

	<h4>Professors</h4>
	
	<table class="table">
		<thead class="thead-default">
			<tr>
				<th>Professor</th>
				<th>Professor Info</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${professors}" var="prof">
				<tr>
					<td><c:out value="${prof.name}"/> <c:out value="${prof.lastName}"/></td>
					<td><c:url value="/getProfessorWithSubjects/${prof.id}" var="url"/>
						<a href="${url}">Professor info</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<h4>Requests</h4>
	
	<table class="table">
		<thead class="thead-default">
			<tr>
				<th>Students' Info</th>
				<th>Subject's Info</th>
				<th>Accept Request</th>
				<th>Decline Request</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${subjectRequests}" var="subjectRequest">
				<tr>
					<td><c:out value="${subjectRequest.studentId}"></c:out>
						<c:url value="/getStudentWithSubjects/${subjectRequest.studentId}" var="url"/>
			 			<a href="${url}">Student's info</a></td>
					<td><c:url value="/getSubject/${subjectRequest.subjectId}" var="url"/>
						<a href="${url}">Subject's info</a></td>
					<td><c:url value="/acceptSubjectRequest/${subjectRequest.id}" var="url"/>
					 	<a href="${url}">Accept request</a></td>
					<td><c:url value="/declineSubjectRequest/${subjectRequest.id}" var="url"/>
						<a href="${url}">Decline request</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>