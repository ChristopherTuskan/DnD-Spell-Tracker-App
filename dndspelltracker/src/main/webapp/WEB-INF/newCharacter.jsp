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
<title>New Character</title>
</head>
<style>
body {
background-image: url("../images/DND Background.jpg")
}
</style>
<body>
	<div class="col-3 mx-auto bg-dark p-3 text-white rounded m-3">
		<div class="d-flex justify-content-between">
			<h1 class="m-3 text-danger">New DND Character</h1>
			<div class="d-flex flex-column">
				<div class="d-flex justify-content-end">
					<a class="m-2" href="/logout"><input class="btn-danger" type=button value="Logout"></a>
				</div>
				<a class="m-2" href="/dashboard"><input class="btn-warning" type=button value="Back to Dashboard"></a>
			</div>
		</div>
		<form:form action="/character/create/" method="post" modelAttribute="dndchar">
			<div class="form-group m-3">
				<form:label path="firstName">First Name:</form:label>
				<form:errors class="text-danger" path="firstName"/>
				<form:input path="firstName" class="form-control"/>
			</div>
			<div class="form-group m-3">
				<form:label path="lastName">Last Name:</form:label>
				<form:errors class="text-danger" path="lastName"/>
				<form:input path="lastName" class="form-control"/>
			</div>
			<div class="form-group m-3">
				<form:label path="dndclass">Class:</form:label>
				<form:errors class="text-danger" path="dndclass"/>
				<form:select path = "dndclass" class="form-control">
   					<form:option value = "" label = "- Select -"/>
   					<form:options items = "${classList}" />
				</form:select> 
			</div>
			<div class="form-group m-3">
				<form:label path="level">Level:</form:label>
				<form:errors class="text-danger" path="level"/>
				<form:select path = "level" class="form-control">
				   <form:option value = "0" label = "- Select -"/>
				   <form:options items = "${levelList}" />
				</form:select> 
			</div>
			<input type="submit" value="Submit" class="m-3 bg-warning"/>
		</form:form>
	</div>
</body>
</html>