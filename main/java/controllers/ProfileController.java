package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BlogModel;
import model.UserModel;
import service.BlogService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for user profile operations. Displays user-specific information
 * and their blogs on the profile page.
 * @author Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/profile" })
public class ProfileController extends HttpServlet {

	private BlogService blogService;

	/**
	 * Initializes the ProfileController with required services.
	 */
	public ProfileController() {
		super();
		this.blogService = new BlogService();
	}

	/**
	 * Displays the user profile along with their blogs.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// UserAuth filter already ensures the user is logged in
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// Get user's blogs
		List<BlogModel> userBlogs = blogService.getBlogsByUserId(user.getUserId());
		request.setAttribute("userBlogs", userBlogs);

		// Forward to profile page
		request.getRequestDispatcher("/WEB-INF/pages/profile_page.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests by redirecting to GET.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redirect to GET handler
		response.sendRedirect(request.getRequestURI());
	}
}