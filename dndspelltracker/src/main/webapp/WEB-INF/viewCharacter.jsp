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
<title>View Character</title>
</head>
<style>
body {
background-image: url("../images/DND Background.jpg")
}
</style>
<body>
	<div class="col-5 mx-auto bg-dark p-3 text-white rounded m-3">
		<div class="d-flex justify-content-between">
			<div class="d-flex flex-column">
				<h1 class="m-3 text-danger">${character.getName()}</h1>
				<div class="d-flex">
					<h3 class="m-3 text-warning">Class: ${character.getDndclass()}</h3>
					<h3 class="m-3 text-warning">Level: ${character.getLevel()}</h3>
				</div>
			</div>
			<div class="d-flex flex-column">
				<div class="d-flex justify-content-end">
					<a class="m-2" href="/logout"><input class="btn-danger" type=button value="Logout"></a>
				</div>
				<a class="m-2" href="/dashboard"><input class="btn-warning" type=button value="Back to Dashboard"></a>
			</div>
		</div>
		<c:choose>
			<c:when test="${character.getSpells().isEmpty()}">
				<div class="d-flex">
					<h3 class="m-3">${character.getDndclass()}s do not have Spells!!!</h3>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach var="spell" items="${character.getSpells()}">
					<div>
						<c:choose>
							<c:when test="${spell.getLevel() == 0 && displayedLevels.get(spell.getName()) == true}">
								<h3 class="mt-3 mb-0 bg-danger text-dark border border-dark">Cantrips:</h3>
							</c:when>
							<c:when test="${spell.getLevel() >= 0 && displayedLevels.get(spell.getName()) == true}">
								<h3 class="mt-3 mb-0 bg-danger text-dark border border-dark">Level ${spell.getLevel()} Spells:</h3>
							</c:when>
						</c:choose>
							<button id="spellbtn" data-clickcount="0" class="col-12 mx-auto" type="button" onclick="displaySpellDesc('${spell.getName()}', this)">${spell.getName()}</button>
							<div class="bg-danger mb-1 border border-dark" style="display:none;" id="${spell.getName()}">${spell.getDesc()}</div>
					</div>
		    	</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
<script>
	function displaySpellDesc(spellName, identifier){
		document.getElementById("spellbtn").addEventListener('click', clickHandler(spellName, identifier));
	}
	
	function clickHandler(spellName, identifier, event){
		if (identifier.dataset.clickcount%2 == 0) {
			document.getElementById(spellName).style.display = 'block';
		}
		else {
			document.getElementById(spellName).style.display = 'none';
		}
		identifier.dataset.clickcount++;
	}
</script>
</html>