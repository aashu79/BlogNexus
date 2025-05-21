package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class Logout. Handles user logout functionality by
 * invalidating the session and forwarding to a logout page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/logout" })
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the Logout controller.
	 */
	public Logout() {
		super();
	}

	/**
	 * Handles GET requests for logout functionality. Invalidates the session and
	 * forwards the user to a logout page with animation.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session != null) {
			// Invalidate the session to clear all session attributes
			session.invalidate();
		}

		// Forward to the logout page with animation
		request.getRequestDispatcher("/WEB-INF/pages/logout.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to the doGet method.
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