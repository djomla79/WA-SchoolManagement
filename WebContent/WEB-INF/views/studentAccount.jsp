<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>Student Account</title>
</head>
<body>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
	<div class="row">
         <div class="well">
         	<h3>Welcome <c:out value="${student.name}"/> <c:out value="${student.lastName}"/></h3>
         </div>
    </div><br>
    
	<h4>Your Subjects</h4>
	
	<table class="table">
		<thead class="thead-default">
			<tr>
				<th>Subject</th>
				<th>Grades and Absences</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${student.studentSubjects}" var="subject">
				<tr>
					<td><c:out value="${subject.subjectName}"/></td>
					<td><spring:url value="/getGradesAndAbsences?student=${student.id}&subject=${subject.id}" var="url" />
						<a href="${url}">Your grades and absences for this subject</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table><br>
	
	<h5>Total average for all grades: <c:out value="${totalAverage}" /></h5>
	<br>
	<h4>Choose Subjects</h4>
	<c:forEach items="${allSubjects}" var="subject">
			<p><c:out value="${subject.subjectName}"/>
				<spring:url value="/sendSubjectRequest?student=${student.id}&subject=${subject.id}" var="url"/>
				<a href="${url}">Send request for this subject</a></p>
	</c:forEach>
	
</body>
</html>