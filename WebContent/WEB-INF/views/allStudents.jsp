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
<title>All Students</title>
</head>
<body>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
	<br><br><br>
	<div class="container">
      <div class="container1">
		<div class="header1"><h3>Students</h3></div>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Student</th>
					<th>Info</th>
					<th>Student's Subject - adding Grades</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${students}" var="student">
					<tr>
						<td><c:out value="${student.name}"/> <c:out value="${student.lastName}"/></td>
						<td><c:url value="/getStudentWithSubjectAndGrades?student=${student.id}&subject=${subject.id}" var="url"/>
							<a href="${url}">Student info</a></td>
						<td>
							<p><spring:url value="/getStudentWithSubjectAndAddGrade?student=${student.id}&subject=${subject.id}" var="url" />
							<a href="${url}">Grade this subject</a></p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
		     <tr>
		       <td colspan="3" style="text-align: center;">The end of the line</td>
		     </tr>
		    </tfoot>
		</table>
		
		</div>
	</div>
</body>
</html>