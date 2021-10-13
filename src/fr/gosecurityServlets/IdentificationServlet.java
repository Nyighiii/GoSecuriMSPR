package fr.gosecurityServlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.cloud.firestore.Firestore;

import fr.gosecuri.pojos.User;
import fr.gosecurityDAO.FireBaseDao;
import fr.gosecurityDAO.UserDao;

/**
 * 
 * Servlet implementation class IdentificationServlet
 */
@WebServlet(name = "Identification", urlPatterns = {"/identification"})
public class IdentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdentificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/identification.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// déclaration variable erreur
		String erreurs = "";

		HttpSession session = request.getSession();

		// gestion des erreurs dans le formulaire
		String pseudo = request.getParameter("pseudo");
		if (pseudo.isEmpty()) {
			erreurs +="merci de remplir le champ peudo<br>";
		}
		String mdp = request.getParameter("mdp");
		if (mdp.isEmpty()) {
			erreurs +="merci de remplir le champ mot de passe<br>";
		}



		
		
		Firestore db = getFbDao();
		
		UserDao uDao = getUserDao(db);
		
		User u = uDao.getUserByLoginPassword(pseudo);
		
		if(u.getPassword() != null && u.getPassword().equals(mdp)) {
			session.setAttribute("user", u);
			
			response.sendRedirect(request.getContextPath() + "/materiel");
		}
		else {
			erreurs +="Identification impossible<br>";
		}
		
		if(!erreurs.isEmpty()) {
			
			// renvoie les messages d'erreurs 
			request.setAttribute("erreurs", erreurs);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/identification.jsp").forward(request, response);
		}
	}	

	/*public Firestore getFbDao() {

		String contextPath = getServletContext().getRealPath("/WEB-INF");
		Firestore currentDao = new FireBaseDao(contextPath).getDbConnexion();
		System.out.println("Connexion avec Firebase OK !!!!");

		return currentDao;

	}*/
	
	
	public Firestore getFbDao() {
		ServletContext cont = getServletContext();
		Firestore  currentDao = (Firestore) cont.getAttribute("myDb");
		if(currentDao == null) {
			String contextPath = cont.getRealPath("/WEB-INF");
			currentDao = new FireBaseDao(contextPath).getDbConnexion();
			cont.setAttribute("myDb", currentDao);
			System.out.println("Connection avec Firebase OK !!!!");
		}
		return currentDao;
	}

	
	public UserDao getUserDao(Firestore db) {

		
		return new UserDao(db);
	}


}
