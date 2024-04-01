package com.tall.services;

import jakarta.servlet.http.HttpServletRequest;

public class ConnectionForm {

	//Pour se connecter suffit de mettre un nom d'utilisateur et ajouter à ce dernier 123 comme mot de passe
	public boolean verifierIdentifiant(HttpServletRequest request ) {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		if (pass != null && pass.equals(login + 123)) {
			return true;
		}
		else {
			return false;
		}
	}

}