package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PrivacyPolicy. Handles requests for the Privacy
 * Policy page.
 * @author Bishal Kumar Rajak
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/privacy-policy" })
public class PrivacyPolicy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the PrivacyPolicy controller.
	 */
	public PrivacyPolicy() {
		super();
	}

	/**
	 * Handles GET requests for the Privacy Policy page.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward to the Privacy Policy JSP page
		request.getRequestDispatcher("/WEB-INF/pages/privacy_policy.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for the Privacy Policy page. Delegates processing to
	 * the doGet method.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}