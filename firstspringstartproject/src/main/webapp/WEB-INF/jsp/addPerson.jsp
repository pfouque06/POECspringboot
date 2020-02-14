<%@ page language="java" contentType="text/html; charset=
UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page</title>
</head>
<body>
	<h2>Adding a new person</h2>
		<br>
	<form action="addPerson" method="post">
		Nom : <input type="text" name="nom"><br>
		Prénom : <input type="text" name="prenom"><br>
		<button type="submit">Envoyer</button>
	</form>
</body>
</html>
