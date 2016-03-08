<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>
<body>
	
	<h5>Register Admin</h5>
	<spring:url value="/registerAdmin" var="formUrl" />
    <form:form modelAttribute="user" action="${formUrl}" method="POST">
          
          <div>
			  <label>Name: </label><br />
	          <form:input path="name" name="name" type="text" /><br />
	          <label>Last Name: </label><br />
	          <form:input type="text" path="lastName" name="lastName" /><br />
	          <label>Username: </label><br />
	          <form:input type="text" path="username" name="username" /><br />
	          <label>Password: </label><br />
	          <form:input type="text" path="password" name="password" /><br />
	          
	          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          </div><br />
          
          <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
          
      </form:form>
      <br> <br>
      <h5>Register Professor</h5>
	  <spring:url value="/registerProfessor" var="formUrl" />
      <form:form modelAttribute="user" action="${formUrl}" method="POST">
          
            <div>
			    <label>Name: </label><br />
	            <form:input path="name" name="name" type="text" /><br />
	            <label>Last Name: </label><br />
	            <form:input type="text" path="lastName" name="lastName" /><br />
	            <label>Username: </label><br />
	            <form:input type="text" path="username" name="username" /><br />
	            <label>Password: </label><br />
	            <form:input type="text" path="password" name="password" /><br />
	          
	            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div><br />
          
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
          
        </form:form>
        <br> <br>
        <h5>Register Student</h5>
        <spring:url value="/registerStudent" var="formUrl" />
        <form:form modelAttribute="user" action="${formUrl}" method="POST">
          
              <div>
<!--           	  <label>Name: </label><br /> -->
<!--           	  <input type="text" name="name" placeholder="Name" class="required" title="Please, enter your first name." /><br /> -->
<!-- 	          <label>Last Name: </label><br /> -->
<!-- 	          <input type="text" name="lastName" placeholder="Last Name" class="required" title="Please, enter your last name." /><br /> -->
<!-- 	          <label>Username: </label><br /> -->
<!-- 	          <input type="text" name="username" placeholder="Username" /><br /> -->
<!-- 	          <label>Password: </label><br /> -->
<!-- 	          <input type="password" name="password" placeholder="Password" /><br /> -->
			      <label>Name: </label><br />
	              <form:input path="name" name="name" type="text" /><br />
	              <label>Last Name: </label><br />
	              <form:input type="text" path="lastName" name="lastName" /><br />
	              <label>Username: </label><br />
	              <form:input type="text" path="username" name="username" /><br />
	              <label>Password: </label><br />
	              <form:input type="text" path="password" name="password" /><br />
	          
	              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              </div><br />
          
              <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
          
          </form:form>
</body>
</html>