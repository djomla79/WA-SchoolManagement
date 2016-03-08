<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Account</title>
</head>
<body>
	
	<h3>Welcome <c:out value="${student.name}"/> <c:out value="${student.lastName}"/></h3>
	
	<br>
	<h4>Your Subjects</h4>
	<c:forEach items="${student.studentSubjects}" var="subject">
			<c:out value="${subject.subjectName}"/>
			<p><spring:url value="/getGrades?student=${student.id}&subject=${subject.id}" var="url" />
			<a href="${url}">Grades for this subject</a></p>
			<p><spring:url value="/getAbsences?student=${student.id}&subject=${subject.id}" var="url" />
			<a href="${url}">Absences for this subject</a></p>
<%-- 		<a href="<c:url value="subject?subjectName=${subject.subjectName}" />"><c:out value="${subject.subjectName}"></c:out></a> --%>
<%-- 		<a href="<c:url value="/${subject.subjectName}" />"><c:out value="${subject.subjectName}"></c:out></a> --%>
			<br>
	</c:forEach>
	
	<br>
<!-- 	<h4> Your Grades</h4> -->
<%-- 	<c:forEach items="${student.studentGrades}" var="grade"> --%>
<%-- 	<c:forEach items="${gradeValues}" var="grade"> --%>
<%-- 			<c:out value="${grade.gradeValues}"/> --%>
<%-- 		<p><spring:url value="/getGrades?student=${student.id}&grade=${grade.id}" var="url" /> --%>
<%-- 			<a href="${url}"><c:out value="${grade.id}"/></a></p> --%>
<%-- 		</c:forEach> --%>
	
	<h4>Choose Subjects</h4>
	<c:forEach items="${allSubjects}" var="subject">
			<p><c:out value="${subject.subjectName}"/>
<%-- 			<spring:url value="/getSubjectToStudent?student=${student.id}&subject=${subject.id}" var="url" /> --%>
<%-- 			<a href="${url}">Choose this subject</a></p> --%>
				<spring:url value="/sendSubjectRequest?student=${student.id}&subject=${subject.id}" var="url"/>
				<a href="${url}">Send request for this subject</a></p>
	</c:forEach>
	
	<p><a href="<spring:url value="/logout"/>">Log out</a></p>
	
</body>
</html>