package fr.gosecurityServlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.firestore.Firestore;

import fr.gosecurityDAO.FireBaseDao;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(name = "Index", urlPatterns = {"/index", ""})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getFbDao();
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		
		
		//String contextPath = getServletContext().getRealPath("/WEB-INF");
		//Firestore dao = new FireBaseDao(contextPath).getDbConnexion();
		//System.out.println("Connection avec Firebase OK !!!!");
		
		return currentDao;
	}
}
