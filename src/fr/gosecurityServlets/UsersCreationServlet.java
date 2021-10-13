package fr.gosecurityServlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.GetUsersResult;

import fr.gosecuri.pojos.Materiel;
import fr.gosecuri.pojos.Mouvement;
import fr.gosecuri.pojos.User;
import fr.gosecurityDAO.FireBaseDao;
import fr.gosecurityDAO.MaterielDao;
import fr.gosecurityDAO.MouvementDao;
import fr.gosecurityDAO.UserCreationDao;

/**
 * Servlet implementation class UsersCreationServlet
 */

@WebServlet(name = "UsersCreationServlet", urlPatterns = {"/userscreation"})

public class UsersCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsersCreationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		this.getServletContext().getRequestDispatcher("/WEB-INF/usersCreation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Firestore db = getFbDao();

		UserCreationDao userCreateDao = getUserCreationDao(db);
		String id;


		
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String password = request.getParameter("password");
		// Attention conversion date pour récupérer les paramètres de saisie
		String photo = request.getParameter("photo");
		// Boolean isAdmin = request.getParameter("IsAdmin");
		
		
		User newUser = new User();
		userCreateDao.createNewUser(newUser);
		}



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


public UserCreationDao getUserCreationDao(Firestore db) {

	return new UserCreationDao(db);
}





}
