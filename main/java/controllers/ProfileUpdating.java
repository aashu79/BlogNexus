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
 * Servlet implementation class ProfileUpdating
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/profile/update" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 10,  // 10 MB
    maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class ProfileUpdating extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfileUpdatingService profileService;
       
    /**
     * Initializes servlet and service
     */
    public ProfileUpdating() {
        super();
        this.profileService = new ProfileUpdatingService();
    }

    /**
     * Handles GET request to display the profile update form
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/profile_updating.jsp").forward(request, response);
    }

    /**
     * Handles POST request to process profile updates
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        profileService.updateProfile(request, response);
    }
}