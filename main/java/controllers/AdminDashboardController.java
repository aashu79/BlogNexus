package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserModel;
import service.DashboardService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller for admin dashboard. Provides data and handles requests for the
 * admin dashboard page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/dashboard" })
public class AdminDashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DashboardService dashboardService;

	/**
	 * Initializes the AdminDashboardController with the DashboardService instance.
	 */
	public AdminDashboardController() {
		super();
		this.dashboardService = new DashboardService();
	}

	/**
	 * Handles GET requests for the dashboard page. Retrieves statistics, recent
	 * blogs, and recent users for display.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		UserModel user = (UserModel) session.getAttribute("user");

		// Get dashboard data
		Map<String, Integer> stats = dashboardService.getDashboardStats();
		List<model.BlogModel> recentBlogs = dashboardService.getRecentBlogs(5);
		List<model.UserModel> recentUsers = dashboardService.getRecentUsers(5);
		String currentDateTime = dashboardService.getCurrentDateTime();

		// Set attributes for JSP
		request.setAttribute("stats", stats);
		request.setAttribute("recentBlogs", recentBlogs);
		request.setAttribute("recentUsers", recentUsers);
		request.setAttribute("currentDateTime", currentDateTime);
		request.setAttribute("username", user.getFirstName());

		// Forward to JSP
		request.getRequestDispatcher("/WEB-INF/pages/admin_dashboard.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests. Delegates processing to the doGet method.
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