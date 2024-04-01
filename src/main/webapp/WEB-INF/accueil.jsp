<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Liste des utilisateurs</title>
	<link href= "css/styles.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<%@ include file="menu.jsp" %>
		<h2>Bienvenue</h2>
	    <p>Vous êtes connecté en tant que <%= session.getAttribute("username") %>.</p>
	    
	    <h2>Ajouter un utilisateur</h2>
	    
		<form method="post" action="accueil">
			<input type="hidden" name="action" value="ajouter">
	        <p>
	            <label for="nom">Nom : </label>
	            <input type="text" name="nom" id="nom" required/>
	        </p>
	        <p>
	            <label for="prenom">Prénom : </label>
	            <input type="text" name="prenom" id="prenom" required/>
	        </p> 
	        <input type="submit" value="Ajouter" />
	    </form>
	  	<c:if test="${param.utilisateurAjoute}">
			        <p style="color: green;"><c:out value="L'utilisateur ${dernierAjout} a été ajouté avec succes !!!" /></p>
	    </c:if>
	    <c:if test="${utilisateurModifie}">
			        <p style="color: green;"><c:out value="L'utilisateur a été modifié avec succes !!!" /></p>
	    </c:if>
	    <c:if test="${utilisateurSupprimer}">
			        <p style="color: green;"><c:out value="L'utilisateur a été supprimé avec succes !!!" /></p>
	    </c:if>
	    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
	    
	    <h2>Liste des utilisateurs :</h2>
	    <table class="center-table">
	    	<tr>
	            <th>Prénom</th>
	            <th>Nom</th>
	            <th>Action</th>
	        </tr>
		    <c:forEach items="${ utilisateurs }" var="utilisateur">
			    <tr>
			    	<td><c:out value="${ utilisateur.prenom }" /></td>
			    	<td><c:out value="${ utilisateur.nom }" /></td>
			    	<td>
			    		<div class="action-form">
				    		<form method="post" action="accueil">
					    		<input type="hidden" name="id" value="${utilisateur.id}">
					    		<input type="hidden" name="action" value="supprimer">
				   				<input type="submit" value="Supprimer">
				   			</form>
		  	    			<form method="post" action="accueil">
					    		<input type="hidden" name="userId" value="${utilisateur.id}">
					    		<input type="hidden" name="action" value="modifier">
			    				<input type="submit" value="Modifier">
				    		</form>
			    		</div>
			    	</td>
			    </tr>
		    </c:forEach>
    `	</table>
    </div>   
</body>
</html>