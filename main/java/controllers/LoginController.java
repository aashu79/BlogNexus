package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import service.LoginService;
import utils.RedirectionUtil;

import java.io.IOException;

/**
 * Servlet implementation for the LoginController. Handles login functionality,
 * including authentication and redirection based on user roles.
 * @author Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private static LoginService loginService;
	private static RedirectionUtil redirectionUtil;

	private static final long serialVersionUID = 1L;

	/**
	 * Initializes the LoginController with LoginService and RedirectionUtil.
	 */
	public LoginController() {
		super();
		loginService = new LoginService();
		redirectionUtil = new RedirectionUtil();
	}

	/**
	 * Handles GET requests for the login page. Redirects logged-in users to
	 * appropriate pages based on their roles.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check if user is already logged in
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");

			// Redirect based on user role
			if ("admin".equalsIgnoreCase(user.getUserRole())) {
				// Admin users go to the dashboard
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
			} else {
				// Regular users go to the home page
				response.sendRedirect(request.getContextPath() + "/");
			}
			return;
		}

		// If not logged in, show the login page
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for user authentication. Delegates authentication to
	 * the LoginService and manages error handling.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			loginService.authenticateUser(request, response);
		} catch (Exception e) {
			System.err.println(e);
			redirectionUtil.redirectWithMessage(request, response, "login.jsp", "error", "Something went wrong!");
		}
	}
}