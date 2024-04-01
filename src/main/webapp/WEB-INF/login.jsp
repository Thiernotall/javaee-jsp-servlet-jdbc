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
			<h1>Connexion </h1>
		
			<form method="post" action="login">
		        <p>
		            <label for="login">Login : </label>
		            <input type="text" name="login" id="login" />
		        </p>
		        <p>
		            <label for="pass">Password : </label>
		            <input type="password" name="pass" id="pass" />
		        </p>
		        <button type="submit">Se connecter</button>
		    </form>
		    <c:if test="${not empty errorMessage}">
		        <p style="color: red;"><c:out value="${errorMessage}" /></p>
		    </c:if>
		</div>
	</body>
</html>