package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import model.BlogModel;
import service.SearchService;

/**
 * Servlet implementation for the SearchController. Handles search functionality
 * to display search results for blogs.
 * @author Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/search" })
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SearchService searchService;

	/**
	 * Initializes the SearchController with the SearchService.
	 */
	public SearchController() {
		super();
		this.searchService = new SearchService();
	}

	/**
	 * Handles GET requests to serve the search results page.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get search query parameter
		String query = request.getParameter("query");

		// Redirect to home if query is null or empty
		if (query == null || query.trim().isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		// Decode URL-encoded query
		String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);

		// Get search results
		Map<String, Object> result = searchService.searchBlogs(decodedQuery);
		List<BlogModel> blogs = (List<BlogModel>) result.get("blogs");
		Map<Integer, Map<String, String>> authorDetails = (Map<Integer, Map<String, String>>) result
				.get("authorDetails");

		// Set attributes for JSP
		request.setAttribute("searchQuery", decodedQuery);
		request.setAttribute("searchResults", blogs);
		request.setAttribute("authorDetails", authorDetails);

		// Forward to JSP
		request.getRequestDispatcher("/WEB-INF/pages/search_page.jsp").forward(request, response);
	}
}