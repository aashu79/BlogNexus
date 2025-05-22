package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.BlogModel;
import model.UserModel;
import service.UserFavouriteService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing user's favorite blogs. Handles displaying and
 * removing blogs from the user's favorites list.
 * @author Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/favourite", "/user/favourite/remove" })
public class Favourite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserFavouriteService favouriteService;

	/**
	 * Initializes the Favourite controller with UserFavouriteService.
	 */
	public Favourite() {
		super();
		this.favouriteService = new UserFavouriteService();
	}

	/**
	 * Displays the user's favorite blogs.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check if user is logged in
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		UserModel currentUser = (UserModel) session.getAttribute("user");
		int userId = currentUser.getUserId();

		// Get user's favorite blogs
		List<BlogModel> favouriteBlogs = favouriteService.getFavouriteBlogsByUserId(userId);

		// Prepare additional data for display
		Map<Integer, String> readTimes = new HashMap<>();
		Map<Integer, String> excerpts = new HashMap<>();
		Map<Integer, String> authorRoles = new HashMap<>();
		Map<Integer, String> formattedDates = new HashMap<>();

		for (BlogModel blog : favouriteBlogs) {
			int blogId = blog.getBlogId();

			// Calculate read time
			int minutes = favouriteService.calculateReadTime(blog.getContent());
			readTimes.put(blogId, minutes + " min read");

			// Create excerpt
			String excerpt = favouriteService.generateExcerpt(blog.getContent(), 150);
			excerpts.put(blogId, excerpt);

			// Get author role based on genre
			String authorRole = favouriteService.getAuthorRoleByGenre(blog.getGenre());
			authorRoles.put(blogId, authorRole);

			// Format date
			if (blog.getCreatedAt() != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
				formattedDates.put(blogId, blog.getCreatedAt().format(formatter));
			} else {
				formattedDates.put(blogId, "Unknown date");
			}
		}

		// Format current date/time
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = now.format(formatter);

		// Set attributes for JSP
		request.setAttribute("blogs", favouriteBlogs);
		request.setAttribute("readTimes", readTimes);
		request.setAttribute("excerpts", excerpts);
		request.setAttribute("authorRoles", authorRoles);
		request.setAttribute("formattedDates", formattedDates);
		request.setAttribute("currentDateTime", formattedDateTime);
		request.setAttribute("currentUser", currentUser);

		// Forward to JSP
		request.getRequestDispatcher("/WEB-INF/pages/user_favourite.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for removing favorites. Delegates the request to
	 * UserFavouriteService for removal processing.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();

		if ("/user/favourite/remove".equals(servletPath)) {
			favouriteService.processRemoveFavourite(request, response);
		} else {
			doGet(request, response);
		}
	}
}