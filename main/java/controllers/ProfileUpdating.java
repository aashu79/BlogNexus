package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.ProfileUpdatingService;

import java.io.IOException;

/**
 * Servlet implementation for ProfileUpdating. Handles profile update
 * functionality, including displaying the update form and processing updates.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/profile/update" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class ProfileUpdating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProfileUpdatingService profileService;

	/**
	 * Initializes the ProfileUpdating servlet and its service.
	 */
	public ProfileUpdating() {
		super();
		this.profileService = new ProfileUpdatingService();
	}

	/**
	 * Handles GET requests to display the profile update form.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward to the profile updating JSP page
		request.getRequestDispatcher("/WEB-INF/pages/profile_updating.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests to process profile updates. Delegates the request to
	 * the ProfileUpdatingService for handling.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @throws ServletException If a servlet-specific error occurs
	 * @throws IOException      If an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Delegate the profile update logic to the service
		profileService.updateProfile(request, response);
	}
}