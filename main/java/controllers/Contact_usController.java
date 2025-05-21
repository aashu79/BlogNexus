package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation for the Contact_usController. Handles requests for the
 * "Contact Us" page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contact" })
public class Contact_usController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the Contact_usController.
	 */
	public Contact_usController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Handles GET requests for the "Contact Us" page.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward request to the "Contact Us" JSP page
		request.getRequestDispatcher("/WEB-INF/pages/contact_us.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for the "Contact Us" page. Delegates processing to the
	 * doGet method.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delegate to doGet for processing
		doGet(request, response);
	}

}