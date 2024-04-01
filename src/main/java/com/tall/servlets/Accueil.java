package com.tall.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.tall.beans.Utilisateur;
import com.tall.dao.DaoException;
import com.tall.dao.DaoFactory;
import com.tall.dao.UtilisateurDao;

public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
	    this.utilisateurDao = daoFactory.getUtilisateurDao();
	}
       
    
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			request.setAttribute("erreur", e.getMessage());
		}
    	
        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On recupere l'action souhaitée à travers la requete Post
		String action = request.getParameter("action");
		try {
			//Lorsqu'on veut ajouter un utilisateur
			if ("ajouter".equals(action)) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(request.getParameter("nom"));
				utilisateur.setPrenom(request.getParameter("prenom"));
				
				utilisateurDao.ajouter(utilisateur);
				request.setAttribute("dernierAjout",request.getParameter("prenom") + " " + request.getParameter("nom"));
				request.setAttribute("utilisateurs", utilisateurDao.lister());
				response.sendRedirect("accueil?utilisateurAjoute=true");
			}
			//Lorsqu'on veut supprimer un utilisateur
			if ("supprimer".equals(action)) {
				String id= request.getParameter("id");//On recupere l'id de l'utilisateur qu'on veut supprimer
				
				utilisateurDao.supprimer(id);
				request.setAttribute("utilisateurSupprimer",true );
				request.setAttribute("utilisateurs", utilisateurDao.lister());
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}
			//Lorsqu'on veut supprimer un utilisateur
			if ("modifier".equals(action)) {
				// Récupérez les informations de l'utilisateur en fonction de l'ID envoyé depuis le formulaire
				String userId = request.getParameter("userId");
				Utilisateur utilisateur = utilisateurDao.getUtilisateurById(userId);
				String nom = utilisateur.getNom();
				String prenom = utilisateur.getPrenom();
				
				// Transférez ces informations vers la page de modification
				request.setAttribute("nom",nom);
				request.setAttribute("prenom", prenom);
				request.setAttribute("id", userId);
				this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);

			}
			if ("validerUpdate".equals(action)) {
				try {
					String idString = request.getParameter("id");
					int id = Integer.parseInt(idString);
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNom(request.getParameter("nomModifie"));
					utilisateur.setPrenom(request.getParameter("prenomModifie"));
					utilisateur.setId(id);
					
					utilisateurDao.update(utilisateur);
					request.setAttribute("utilisateurModifie",true);
					request.setAttribute("utilisateurs", utilisateurDao.lister());
					this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
				}catch(Exception e) {
					request.setAttribute("erreur", e.getMessage());
					String id = request.getParameter("id");
					Utilisateur utilisateur = utilisateurDao.getUtilisateurById(id);
					String nom = utilisateur.getNom();
					String prenom = utilisateur.getPrenom();
					
					// Transférez ces informations vers la page de modification
					request.setAttribute("nom",nom);
					request.setAttribute("prenom", prenom);
					request.setAttribute("id", id);
					this.getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(request, response);
				} 

			}
		}catch (Exception e) {
			request.setAttribute("erreur", e.getMessage());
			try {
				request.setAttribute("utilisateurs", utilisateurDao.lister());
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		} 
		
		
	}

}
