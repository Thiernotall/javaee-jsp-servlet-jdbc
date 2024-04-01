package com.tall.beans;

public class Utilisateur {
	
	private int id;

	private String nom;
	
	private String prenom;
	
	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) throws BeanException {
        if (nom.length() > 10) {
            throw new BeanException("Le nom est trop grand ! (10 caractères maximum)");
        }
        else {
            this.nom = nom; 
        }
    }

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
