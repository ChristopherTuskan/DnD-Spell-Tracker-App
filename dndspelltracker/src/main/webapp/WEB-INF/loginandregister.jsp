<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css"/>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<meta charset="ISO-8859-1">
<title>Login And Register</title>
</head>
<style>
body {
background-image: url("../images/DND Background.jpg")
}
</style>
<body>
	<div class="col-6 mx-auto text-white">
		<div class="text-center m-3">
			<h1 class="text-warning display-1">Dungeon and Dragons Spell Tracker</h1>
			<h5 class="text-white font-weight bolder">An easy way to keep track of all your spells</h5>
		</div>
		<div class="d-flex">
			<div class="m-3 bg-dark col-6 rounded">
				<h2 class="m-3 text-warning">Register</h2>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="form-group m-3">
						<form:label path="username">Username:</form:label>
						<form:errors class="text-danger" path="username"/>
						<form:input path="username" class="form-control"/>
					</div>
					<div class="form-group m-3">
						<form:label path="email">Email:</form:label>
						<form:errors class="text-danger" path="email"/>
						<form:input path="email" class="form-control"/>
					</div>
					<div class="form-group m-3">
						<form:label path="password">Password:</form:label>
						<form:errors class="text-danger" path="password"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<div class="form-group m-3">
						<form:label path="confirm">Confirm Password:</form:label>
						<form:errors class="text-danger" path="confirm"/>
						<form:input path="confirm" type="password" class="form-control"/>
					</div>
					<input type="submit" value="Submit" class="m-3 bg-warning"/>
				</form:form>
			</div>
			<div class="m-3 bg-dark col-6 rounded">
				<h2 class="m-3 text-warning">Login</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="form-group m-3">
						<form:label path="email">Email:</form:label>
						<form:errors class="text-danger" path="email"/>
						<form:input path="email" class="form-control"/>
					</div>
					<div class="form-group m-3">
						<form:label path="password">Password:</form:label>
						<form:errors class="text-danger" path="password"/>
						<form:input path="password" type="password" class="form-control"/>
					</div>
					<input type="submit" value="Submit" class="m-3 bg-warning"/>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>