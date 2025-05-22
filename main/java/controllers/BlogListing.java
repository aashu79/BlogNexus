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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for listing blogs in the admin panel. Handles displaying blogs and
 * their associated authors for administrative purposes.
 * @author Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/blogs" })
public class BlogListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BlogService blogService;

	/**
	 * Initializes the BlogListing controller with BlogService.
	 */
	public BlogListing() {
		super();
		this.blogService = new BlogService();
	}

	/**
	 * Handles GET requests to display the blog listing.
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

		// Get all blogs for display
		List<BlogModel> blogs = new ArrayList<>();

		try {
			// Use the getAllBlogs method from BlogService
			// Since it expects to forward to a JSP directly, we'll use a custom method
			// instead
			blogs = getAllBlogsAsList();

			// Get author names for each blog
			Map<Integer, String> authorNames = getAuthorNames(blogs);

			// Set data for JSP
			request.setAttribute("blogs", blogs);
			request.setAttribute("authorNames", authorNames);

			// Format current date and time
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDateTime = now.format(formatter);

			request.setAttribute("currentDateTime", formattedDateTime);
			request.setAttribute("currentUser", currentUser);

			// Forward to the JSP
			request.getRequestDispatcher("/WEB-INF/pages/blog_listing.jsp").forward(request, response);

		} catch (Exception e) {
			System.err.println("Error fetching blogs: " + e.getMessage());
			e.printStackTrace();

			request.setAttribute("errorMessage", "Failed to retrieve blogs: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/pages/blog_listing.jsp").forward(request, response);
		}
	}

	/**
	 * Custom method to get all blogs as a List.
	 * 
	 * @return List of BlogModel objects representing all blogs
	 */
	private List<BlogModel> getAllBlogsAsList() {
		// This would normally call blogService.getAllBlogs, but that method forwards to
		// JSP
		// So we need to reimplement the logic to return a List instead

		String query = "SELECT b.*, u.first_name, u.last_name FROM blog b JOIN user u ON b.created_by = u.user_id";
		query += " ORDER BY b.created_at DESC";

		return blogService.getAllBlogsForAdmin(query);
	}

	/**
	 * Gets author names for each blog.
	 * 
	 * @param blogs List of BlogModel objects for which author names are needed
	 * @return Map of author IDs to author names
	 */
	private Map<Integer, String> getAuthorNames(List<BlogModel> blogs) {
		Map<Integer, String> authorNames = new HashMap<>();

		for (BlogModel blog : blogs) {
			if (!authorNames.containsKey(blog.getCreatedBy())) {
				// Call service to get author name
				String authorName = blogService.getAuthorNameById(blog.getCreatedBy());
				authorNames.put(blog.getCreatedBy(), authorName);
			}
		}

		return authorNames;
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