<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>Professor Account</title>
</head>
<body>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
	<br><br><br>
	<div class="well">
	    <h3>Welcome Professor <c:out value="${loggedProfessor.name}"/> <c:out value="${loggedProfessor.lastName}"/></h3>
    </div>
	
	<div class="container">
      <div class="header1"><h4>Your Subjects</h4></div>
		<table class="table table-striped">
				<thead>
					<tr>
						<th>Subject</th>
						<th>This subject's students</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${loggedProfessor.subjects}" var="subject">
						<tr>
							<td><c:out value="${subject.subjectName}"/></td>
							<td><c:url value="/getSubjectWithStudents/${subject.id}" var="url"/>
								<a href="${url}">Students</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
			     <tr>
			       <td colspan="2" style="text-align: center;">The end of the line</td>
			     </tr>
			    </tfoot>
			</table>
	</div>
</body>
</html>