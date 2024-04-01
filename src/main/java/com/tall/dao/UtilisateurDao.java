package com.tall.dao;

import java.util.List;

import com.tall.beans.Utilisateur;

public interface UtilisateurDao {

	void ajouter(Utilisateur utilisateur) throws DaoException;
	List<Utilisateur> lister() throws DaoException;
	void supprimer(String id) throws DaoException;
	Utilisateur getUtilisateurById(String id) throws DaoException; 
	void update(Utilisateur utilisateur) throws DaoException; 
}
