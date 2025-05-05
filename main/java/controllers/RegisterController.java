package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import service.RegisterService;
import utils.RedirectionUtil;

import java.io.IOException;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService;
    private RedirectionUtil redirectionUtil;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        this.redirectionUtil = new RedirectionUtil();
        this.registerService = new RegisterService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			return; // Important: stop further processing
		}
		
		// If not logged in, show registration page
		request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Also check login status for POST requests to prevent registration attempts by logged-in users
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			UserModel user = (UserModel) session.getAttribute("user");
			
			if ("admin".equalsIgnoreCase(user.getUserRole())) {
				response.sendRedirect(request.getContextPath() + "/admin/dashboard");
			} else {
				response.sendRedirect(request.getContextPath() + "/");
			}
			return;
		}
		
		// Process registration for non-logged-in users
		try {
			registerService.addUser(request, response);
		} catch (Exception e) {
			System.err.println(e);
			redirectionUtil.redirectWithMessage(request, response, "register.jsp", "error", "Something went wrong!");
		}
	}
}