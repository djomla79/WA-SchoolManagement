<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<spring:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
<%-- <link href="<spring:url value="/resources/css/style.css"/>" rel="stylesheet"> --%>
<script src="<spring:url value="/resources/js/jquery.js"/>"></script>
<script src="<spring:url value="/resources/js/bootstrap.js"/>"></script>
<title>addSubjectsToProfessor</title>
</head>
<body>
	<p>addSubjectsToProfessor</p>
	
		<table class="table">
			<thead class="thead-default">
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
		</table>
</body>
</html>