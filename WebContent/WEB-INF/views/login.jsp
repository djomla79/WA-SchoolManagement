<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<%-- 	<link href="<spring:url value="/resources/css/bootstrap.css"/>" rel="stylesheet"> --%>
<%-- 	<link href="<spring:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet"> --%>
<%-- 	<link href="<spring:url value="/resources/css/style1.css"/>" rel="stylesheet"> --%>
	<title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>

	<jsp:include page="/WEB-INF/views/fragments/navbar.jsp" />
	
	<div class="row">
         <div class="well"><h2>Enter your username and password</h2>
         </div>
     </div>
	
    <div class="container" >
      <div class="container1">
      		
      		<form name='loginForm' action='${pageContext.request.contextPath}/login' method='post'>

				<div class="row">

					Username: <br /> <input type="text" name="username" placeholder="Username" value=""><br />
					Password: <br /> <input type="password" name="password" placeholder="Password"><br />
<!-- 					Remember Me: <input type="checkbox" name="remember-me" /> -->
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					
				</div>
				
				<br/><button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button> 
				 
			</form>

		</div>
	</div>
	
</body>
</html>