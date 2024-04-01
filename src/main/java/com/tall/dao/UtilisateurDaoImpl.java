package com.tall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tall.dao.DaoException;
import com.tall.dao.DaoFactory;
import com.tall.beans.BeanException;
import com.tall.beans.Utilisateur;
import com.tall.dao.UtilisateurDao;

public class UtilisateurDaoImpl implements UtilisateurDao{
	
	private DaoFactory daoFactory;

	public UtilisateurDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Utilisateur utilisateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = connexion.prepareStatement("INSERT INTO utilisateurs(nom, prenom) values(?,?);");
			
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			 
			
			preparedStatement.executeUpdate();
			connexion.commit();
		}catch(SQLException e) {
			try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
		}
	}

	@Override
	public List<Utilisateur> lister() throws DaoException{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		
		Connection connexion = null;
		Statement statement =null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT  id,nom,prenom from utilisateurs;");
			
			while(resultat.next()) {
				Utilisateur utilisateur = new Utilisateur();
				
				utilisateur.setId(resultat.getInt("id"));
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				utilisateurs.add(utilisateur);
				}
		}catch(SQLException e){
			throw new DaoException("Impossible de communiquer avec la base de données");
        } catch (BeanException e) {
            throw new DaoException("Les données de la base sont invalides");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }}
		
		
		return utilisateurs;
	}
	

	@Override
	public void supprimer(String id) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = connexion.prepareStatement("DELETE FROM utilisateurs WHERE id=?;");
			
			preparedStatement.setString(1, id);
			
			preparedStatement.executeUpdate();
			connexion.commit();
		}catch(SQLException e) {
			try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
		}
		
	}

	@Override
	public Utilisateur getUtilisateurById(String id) throws DaoException {
		Utilisateur utilisateur = new Utilisateur();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = connexion.prepareStatement("SELECT id,nom,prenom FROM utilisateurs WHERE id=?;");
			
			preparedStatement.setString(1, id);
			
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) { // Vérifiez si un enregistrement a été retourné
			    utilisateur.setId(resultat.getInt("id"));
			    try {
					utilisateur.setNom(resultat.getString("nom"));
				} catch (BeanException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    utilisateur.setPrenom(resultat.getString("prenom"));
			}
		}catch(SQLException e) {
			try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
		}
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnexion();
			preparedStatement = connexion.prepareStatement(" UPDATE utilisateurs SET nom = ?, prenom = ? WHERE id = ?;");
			
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			preparedStatement.setInt(3, utilisateur.getId());
			
			preparedStatement.executeUpdate();
			connexion.commit();
		}catch(SQLException e) {
			try {
                if (connexion != null) {
                    connexion.rollback();
                }
            } catch (SQLException e2) {
            }
            throw new DaoException("Impossible de communiquer avec la base de données");
        }
        finally {
            try {
                if (connexion != null) {
                    connexion.close();  
                }
            } catch (SQLException e) {
                throw new DaoException("Impossible de communiquer avec la base de données");
            }
		}
		
	}

}
