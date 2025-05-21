package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class TermsAndCondition. Handles requests for the
 * Terms and Conditions page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/terms-and-condition" })
public class TermsAndCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the TermsAndCondition controller.
	 */
	public TermsAndCondition() {
		super();
	}

	/**
	 * Handles GET requests to display the Terms and Conditions page.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward to the Terms and Conditions JSP page
		request.getRequestDispatcher("/WEB-INF/pages/terms_and_condition.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for the Terms and Conditions page. Delegates processing
	 * to the doGet method.
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