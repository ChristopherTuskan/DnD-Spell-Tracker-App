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
<title>New Spell List</title>
</head>
<style>
body {
background-image: url("../images/DND Background.jpg")
}
</style>
<body>
	<div class="col-12 mx-auto bg-dark p-3 text-white rounded m-3">
		<div class="d-flex">
			<h1 class="m-2 text-danger">Spells for ${dndChar.getName()}</h1>
		</div>
		<form action="/spells/add" method="post">
			<div class="d-flex justify-content-between">
				<c:choose>
					<c:when test="${classSpellList.isEmpty()}">
						<div class="d-flex">
							<h2 class="m-2 text-warning">${dndChar.getDndclass()}s do not have Spells!!!</h2>
							<a href="/dashboard"><input class="m-3 btn-danger" type=button value="Back to the Dashboard"></a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 0}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Cantrips:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 1}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 2}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 3}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 4}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 5}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 6}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 7}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 8}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
						<div class="d-flex flex-column m-2">
							<c:forEach var="spell" items="${classSpellList}">
									<c:if test ="${spell.getLevel() == 9}">
										<c:if test="${displayedLevels.get(spell.getName()) == true}">
											<h4 class="font-weight-bolder text-warning">Level ${spell.getLevel()} Spells:</h4>
										</c:if>
										<div>
											<input name="spells[]" type="checkbox" value="${spell.getName()}"/>
											<label>${spell.getName()}</label>
										</div>
									</c:if>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="${classSpellList.isEmpty() == false}">
				<input class="display-6 m-2 bg-warning" type="submit" value="Submit"/>
			</c:if>
		</form>
	</div>
</body>
</html>