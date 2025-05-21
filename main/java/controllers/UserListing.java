package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserModel;
import service.UserService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for user listing in the admin panel. Displays all users and their
 * details to administrators.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/users" })
public class UserListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	/**
	 * Initializes the UserListing controller with the required service.
	 */
	public UserListing() {
		super();
		this.userService = new UserService();
	}

	/**
	 * Handles GET requests to display the user listing page.
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

		// Get all users
		List<UserModel> users = userService.getAllUsers();

		// Pass data to JSP
		request.setAttribute("users", users);
		request.setAttribute("currentUser", currentUser);
		request.setAttribute("currentDateTime", java.time.LocalDateTime.now().toString());

		// Forward to the user listing JSP page
		request.getRequestDispatcher("/WEB-INF/pages/user_listing.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by delegating to the GET handler.
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