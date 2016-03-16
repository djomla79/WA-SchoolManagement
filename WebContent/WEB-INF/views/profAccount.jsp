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
	
	<div class="row">
	    <div class="well">
	    	<h3>Welcome Professor <c:out value="${loggedProfessor.name}"/> <c:out value="${loggedProfessor.lastName}"/></h3>
	    </div>
    </div>
	<br>
	<div class="row">
	    <div class="well">
	    	<h4>Your Subjects</h4>
	    </div>
    </div>
	
	<div class="container">
      <div class="container1">
		<table class="table">
				<thead class="thead-default">
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
			</table>
		</div>
	</div>
</body>
</html>