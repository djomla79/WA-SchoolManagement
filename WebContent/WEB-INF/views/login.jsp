<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link href="<spring:url value='/resources/css/style1.css'/>" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>Login Page</title>
</head>
<body onload='document.f.username.focus();'>
	
	<jsp:include page="../fragments/navbar.jsp" />
	
	<div class="row">
         <div class="well"><h3>Enter your username and password</h3></div>
    </div>
	
    <div class="container">
      <div class="container1">
      		
      		<form name='f' action='${pageContext.request.contextPath}/login' method='post'>

				<div class="row">

					Username: <br /> <input type="text" name="username" placeholder="Username" value=""><br />
					Password: <br /> <input type="password" name="password" placeholder="Password"><br />
<!-- 					Remember Me: <input type="checkbox" name="remember-me" /> -->
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					
				</div>
				
				<br/><input name="submit" type="submit" value="Login" class="btn btn-sm btn-primary" />
				 
			</form>

		</div>
	</div>
	
</body>
</html>