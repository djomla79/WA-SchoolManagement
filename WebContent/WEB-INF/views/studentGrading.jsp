<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Student Grading</title>
</head>
<body>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
	<br><br><br>
	<div class="well"><h4>Student <c:out value="${student.name}" /> <c:out value="${student.lastName}" /></h4></div>
	
	<div class="container">
		<div class="header1"><h5>Subject <c:out value="${subject.subjectName}" /></h5>
		
			<spring:url value="/addGradeToStudent?student=${student.id}&subject=${subject.id}" var="url" />
			<form action="${url}" method="POST">
				<p>Add Grade</p>
				<div class="checkbox">
		  			<label><input type="checkbox" value="${6}"  name="gradeValue">Grade Six</label>
				</div>
				<div class="checkbox">
		  			<label><input type="checkbox" value="${7}" name="gradeValue">Grade Seven</label>
				</div>
				<div class="checkbox">
		  			<label><input type="checkbox" value="${8}" name="gradeValue">Grade Eight</label>
				</div>
				<div class="checkbox">
		  			<label><input type="checkbox" value="${9}" name="gradeValue">Grade Nine</label>
				</div>
				<div class="checkbox">
		  			<label><input type="checkbox" value="${10}" name="gradeValue">Grade Ten</label>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><br />
	          
	          <button class="btn btn-sm btn-primary" type="submit">Submit</button> 
			</form>
		</div>
	</div>
	
	<div class="well"><spring:url value="/addAbsenceToStudent?student=${student.id}&subject=${subject.id}" var="absenceUrl" />
		<a href="${absenceUrl}">Add Absence for this subject: <c:out value="${subject.subjectName}"/></a>
	</div>
</body>
</html>