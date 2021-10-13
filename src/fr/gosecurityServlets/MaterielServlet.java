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

import fr.gosecuri.pojos.Materiel;
import fr.gosecuri.pojos.Mouvement;
import fr.gosecuri.pojos.User;
import fr.gosecurityDAO.FireBaseDao;
import fr.gosecurityDAO.MaterielDao;
import fr.gosecurityDAO.MouvementDao;


/**
 * Servlet implementation class Materiel
 */
@WebServlet(name = "/Materiel", urlPatterns = {"/materiel"})
public class MaterielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MaterielServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Firestore db = getFbDao();

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");

		MaterielDao mDao = getMaterielDao(db);
		
		

		try {
			List<Materiel> materiels = mDao.getMateriels();

			request.setAttribute("materiels", materiels);
			request.setAttribute("user", user);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.getServletContext().getRequestDispatcher("/WEB-INF/materiel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Firestore db = getFbDao();
		
		MaterielDao mDao = getMaterielDao(db);

		String[] materiels = request.getParameterValues("chkmateriel");

		User user = (User) session.getAttribute("user");
		
		MouvementDao mouvDao = getMouvementDao(db);

		for (int i  = 0;i<materiels.length ; i++) {
			String id = materiels[i];
			try {
				Materiel materiel = mDao.getMateriel(id);
				materiel.setQteEnStock(materiel.getQteEnStock()-1);
				
				mDao.insert(materiel);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mouvement mouvement = new Mouvement();
			mouvement.setLogin(user.getLogin());
			mouvement.setIdMateriel(id);
			mouvement.setDate(new Date());
			mouvement.setIsBorowed(true);
			mouvDao.insert(mouvement);

		}

		session.removeAttribute("user");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);

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


	public MaterielDao getMaterielDao(Firestore db) {


		return new MaterielDao(db);
	}

	public MouvementDao getMouvementDao(Firestore db) {


		return new MouvementDao(db);
	}

}
