<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href= "css/styles.css" rel="stylesheet">
	</head>
	
	<body>
		<div class="container">
			<h1>Modification </h1>
		
			<form method="post" action="accueil">
				<input type="hidden" name="id" value="${id}">
				<input type="hidden" name="action" value="validerUpdate">
		        <p>
		            <label for="nomModifie">Nom : </label>
		            <input type="text" name="nomModifie" id="nomModifie" value="${nom}" required />
		        </p>
		        <p>
		            <label for="prenomModifie">Prénom : </label>
		            <input type="text" name="prenomModifie" id="prenomModifie" value="${prenom}" required />
		        </p> 
		        <input type="submit" value="Valider" />
		        <a href="/GestionUtilisateurs/accueil">
    				<button type="button">Annuler</button>
				</a>
	    	</form>
		    <c:if test="${not empty erreur}">
		        <p style="color: red;"><c:out value="${erreur}" /></p>
		    </c:if>
		</div>
	</body>
	
</html>