<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<spring:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<title>addSubjectsToProfessor</title>
</head>
<body>
	
	<jsp:include page="../fragments/admin-navbar.jsp" />
	
	<div class="container">
		<div class="header1">addSubjectsToProfessor</div>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Professor</th>
					<th>Professor's subjects</th>
					<th>Add Subject to Professor</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${professors}" var="prof">
					<tr>
						<td><c:out value="${prof.name}"/> <c:out value="${prof.lastName}"/></td>
						<td><c:forEach items="${prof.subjects}" var="subject">
							<c:out value="${subject.subjectName}"/>
							<br>
						</c:forEach></td>
						<td>	
							<c:forEach items="${subjects}" var="subject">
								<spring:url value="/addSubjectToProfessor?professor=${prof.id}&subject=${subject.id}" var="url" />
								<a href="${url}"><c:out value="${subject.subjectName}"/></a>
								<br>
							</c:forEach>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
		     <tr>
		       <td colspan="3" style="text-align: center;">The end</td>
		     </tr>
	    	</tfoot>
		</table>
	</div>
   
</body>
</html>