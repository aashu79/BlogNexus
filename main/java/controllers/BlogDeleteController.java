package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BlogService;

import java.io.IOException;

/**
 * Controller for blog deletion.
 * @author Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/blog/delete" })
public class BlogDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BlogService blogService;

	/**
	 * Initializes controller with blog service.
	 */
	public BlogDeleteController() {
		super();
		this.blogService = new BlogService();
	}

	/**
	 * Handles deletion requests via GET (not allowed).
	 * 
	 * @param request  HTTP request
	 * @param response HTTP response
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Redirect to profile page
		response.sendRedirect(request.getContextPath() + "/user/profile");
	}

	/**
	 * Processes blog deletion request.
	 * 
	 * @param request  HTTP request with blog ID
	 * @param response HTTP response for redirection
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String blogId = request.getParameter("id");
		if (blogId != null && !blogId.isEmpty()) {
			blogService.deleteBlog(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/user/profile");
		}
	}
}