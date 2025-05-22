package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BlogService;

import java.io.IOException;

/**
 * Controller for blog creation. Handles displaying the blog creation form and
 * processing blog creation submissions.
 * @author Sanjok Shrestha & Aashu Kumar Sah
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/blog/create" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 5 * 1024 * 1024, // 5MB
		maxRequestSize = 10 * 1024 * 1024 // 10MB
)
public class Blog_creationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BlogService blogService;

	/**
	 * Initializes the Blog_creationController with BlogService.
	 */
	public Blog_creationController() {
		super();
		this.blogService = new BlogService();
	}

	/**
	 * Displays the blog creation form.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Display blog creation form
		request.getRequestDispatcher("/WEB-INF/pages/blog_creation.jsp").forward(request, response);
	}

	/**
	 * Processes the blog creation form submission.
	 * 
	 * @param request  The HTTP request with blog data
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Process the form submission to create a new blog
		blogService.createBlog(request, response);
	}
}