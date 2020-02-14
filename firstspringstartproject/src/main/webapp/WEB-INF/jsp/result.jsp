<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result page</title>
</head>
<body>
	<h1>List of persons</h1>
	<c:forEach items="${ personnes }" var="personne">
		<div>
			<c:out value="${ personne.prenom } ${ personne.nom }" />
		</div>
	</c:forEach>
</body>
</html>