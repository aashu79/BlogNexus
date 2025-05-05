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
 * Servlet implementation class LoginController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	
	private static LoginService loginService;
    private static RedirectionUtil redirectionUtil;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		loginService = new LoginService();
        redirectionUtil = new RedirectionUtil();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Check if user is already logged in
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			
			// Redirect based on user role
			if ("admin".equalsIgnoreCase(user.getUserRole())) {
				// Admin users go to dashboard
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
			} else {
				// Regular users go to home page
				response.sendRedirect(request.getContextPath() + "/");
			}
			return;
		}
		
		// If not logged in, show the login page
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
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