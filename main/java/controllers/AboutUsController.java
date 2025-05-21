package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AboutUsController This controller handles
 * requests for the "About Us" page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/about" })
public class AboutUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. Initializes the AboutUsController.
	 */
	public AboutUsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles GET requests. Forwards the request to the "About Us" JSP page.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/pages/about_us.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests. Delegates to the doGet method for processing.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}