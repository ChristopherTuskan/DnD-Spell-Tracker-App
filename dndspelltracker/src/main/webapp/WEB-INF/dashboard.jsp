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
<title>Dashboard</title>
</head>
<style>
body {
background-image: url("../images/DND Background.jpg")
}
</style>
<body>
	<div class="col-5 mx-auto p-3 m-3 bg-dark rounded">
		<div class="d-flex justify-content-between">
			<h1 class="m-3 text-danger">Welcome, ${user.getUsername()}!</h1>
			<a href="/logout"><input class="btn-danger" type=button value="Logout"></a>
		</div>
		<table class="table table-bordered text-white">
			<tr>
				<th>Character Name</th>
				<th>Class</th>
				<th>Level</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="character" items="${characters}">
				<tr>
					<td><a href="/character/view/${character.getId()}"><input class="btn-dark" type=button value="${character.getName()}"></a></td>
					<td><c:out value="${character.getDndclass()}"></c:out></td>
					<td><c:out value="${character.getLevel()}"></c:out></td>
					<td><a href="/character/edit/${character.getId()}"><input class="btn-primary" type=button value="Edit"></a> <a href="/character/destroy/${character.getId()}"><input class="btn-danger" type=button value="Delete"></a></td>
				</tr>
    		</c:forEach>
		</table>
		<a href="/character/new/"><input class="m-3 btn-warning" type=button value="Create New Dungeon and Dragons Character"></a>
	</div>
</body>
</html>