package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserModel;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Controller for handling user addition in admin panel. Handles both display of
 * the add user form and user creation functionality.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/users/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * Initializes the AddUser controller with the UserService instance.
	 */
	public AddUser() {
		super();
		this.userService = new UserService();
	}

	/**
	 * Displays the add user form.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Verify admin access
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		UserModel currentUser = (UserModel) session.getAttribute("user");
		if (!"admin".equals(currentUser.getUserRole())) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		// Format current date and time
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		// Set attributes for JSP
		request.setAttribute("currentDateTime", formattedDateTime);
		request.setAttribute("currentUser", currentUser);

		// Forward to JSP
		request.getRequestDispatcher("/WEB-INF/pages/add_user.jsp").forward(request, response);
	}

	/**
	 * Handles user creation from form submission.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Verify admin access
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		UserModel currentUser = (UserModel) session.getAttribute("user");
		if (!"admin".equals(currentUser.getUserRole())) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		// Delegate to service for user creation
		userService.createUser(request, response);
	}
}