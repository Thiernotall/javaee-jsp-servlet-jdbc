package com.tall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tall.dao.DaoFactory;


public class DaoFactory {

	private String url;
	private String username;
	private String password;
	
	
	public DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DaoFactory getInstance() {
		
		//Chargement du driver 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
		}
		
		//URL de la base de données, le nom d'utilisateur et le mot de passe
		DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/javaee", "user", "password");
		return instance;

	}
	
	// Récupèrer une connexion à la base de données
	public Connection getConnexion() throws SQLException{
		Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion;
	}
	
	 // Récupération du Dao
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl(this);
    }
}
