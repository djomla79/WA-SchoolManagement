<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Register Professor Page</title>
</head>
<body>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
	<div class="container">
		<div id="header1">Professor Registration</div>
	</div>
	
    <div class="container" >
      <div class="container1">
     
      <spring:url value="/registerProfessor" var="formUrl" />
      <sf:form modelAttribute="professor" action="${formUrl}" method="POST">
          
          <div class="row">
          
          	  <label>Name: </label><br />
          	  <sf:input type="text" path="name" name="name" placeholder="Name" class="required" title="Please, enter your first name." /><br />
          	  <sf:errors path="name" cssClass="error"></sf:errors><br />
	          <label>Last Name: </label><br />
	          <sf:input type="text" path="lastName" name="lastName" placeholder="Last Name" class="required" title="Please, enter your last name." /><br />
	          <sf:errors path="lastName" cssClass="error"></sf:errors><br />
	          <label>Username: </label><br />
	          <sf:input type="text" path="username" name="username" placeholder="Username" /><br />
	          <sf:errors path="username" cssClass="error"></sf:errors><br />
	          <label>Password: </label><br />
	          <sf:input type="password" path="password" name="password" placeholder="Password" /><br />
	          <sf:errors path="password" cssClass="error"></sf:errors><br />
	          
	          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	          
          </div><br />
          
          <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
          
      </sf:form>
      </div>
      <div id="messageError">
        <c:out value="${messageError}" />
      </div>
      <div id="messageOk">
        <c:out value="${messageOk}" />
      </div>
    </div>
    
</body>
</html>