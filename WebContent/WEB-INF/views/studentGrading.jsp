<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style1.css" />" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Student Grading</title>
</head>
<body>
	
	<h3>Student <c:out value="${student.name}" /> <c:out value="${student.lastName}" /></h3>
		
	<h4>Student Subjects</h4>
	<c:forEach items="${student.studentSubjects}" var="subject">		
		<c:out value="${subject.subjectName}" /><br>
	
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
          
          <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
		</form>
		<p>Add Absence for this subject:</p>
		<spring:url value="/addAbsenceToStudent?student=${student.id}&subject=${subject.id}" var="absenceUrl" />
		<a href="${absenceUrl}"><c:out value="${subject.subjectName}"/></a>
	</c:forEach>
	
<!-- 	<table class="table"> -->
<!-- 			<thead class="thead-default"> -->
<!-- 				<tr> -->
<!-- 					<th>Student</th> -->
<!-- 					<th>Student's subjects</th> -->
<!-- 					<th>Add Grade to Student</th> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach items="${student.studentSubjects}" var="subject"> --%>
<!-- 					<tr> -->
<%-- 						<td><c:out value="${student.name}" /> <c:out value="${student.lastName}" /></td> --%>
<%-- 						<td><c:out value="${subject.subjectName}" /><br></td> --%>
<!-- 						<td>	 -->
<%-- 							<spring:url value="/addGradeToStudent?student=${student.id}&subject=${subject.id}" var="url" /> --%>
<%-- 							<a href="${url}"><c:out value="${subject.subjectName}"/></a> --%>
<!-- 							<br> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
	
</body>
</html>