package com.tall.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.tall.dao.DaoFactory;
import com.tall.dao.DaoException;
import com.tall.dao.UtilisateurDao;
import com.tall.services.ConnectionForm;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ConnectionForm connectionForm;
	private UtilisateurDao utilisateurDao;
	
	public void init() throws ServletException {
		connectionForm = new ConnectionForm(); // Création de l'instance du service
		
		DaoFactory daoFactory = DaoFactory.getInstance();
	    this.utilisateurDao = daoFactory.getUtilisateurDao();
	}
       
    
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isValider;
		isValider = connectionForm.verifierIdentifiant(request);
		
		if(isValider) {
			HttpSession session = request.getSession();
	        session.setAttribute("username", request.getParameter("login"));
	        try {
				request.setAttribute("utilisateurs", utilisateurDao.lister());
				response.sendRedirect("accueil");
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
			}
	        
		}
		
		else {
			request.setAttribute("errorMessage", "Identifiant ou mot de passe incorrect");
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			
		}
	}

}
