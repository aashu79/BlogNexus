package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserModel;
import service.UserService;
import utils.RedirectionUtil;

import java.io.IOException;

/**
 * Controller for user deletion in admin panel
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/users/delete" })
public class UserDeletionController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private RedirectionUtil redirectionUtil;
       
    /**
     * Initialize controller with service and utilities
     */
    public UserDeletionController() {
        super();
        this.userService = new UserService();
        this.redirectionUtil = new RedirectionUtil();
    }

    /**
     * Handle POST requests for user deletion
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verify admin access
        HttpSession session = request.getSession(false);
   
        
        UserModel currentUser = (UserModel) session.getAttribute("user");

        
        // Get user ID to delete
        String userIdParam = request.getParameter("userId");
        if (userIdParam == null || userIdParam.trim().isEmpty()) {
            redirectionUtil.urlRedirectWithMessage(request, response, "/admin/users", "error", "User ID is required for deletion");
            return;
        }
        
        try {
            int userId = Integer.parseInt(userIdParam);
            
            // Prevent self-deletion
            if (userId == currentUser.getUserId()) {
                redirectionUtil.urlRedirectWithMessage(request, response, "/admin/users", "error", "You cannot delete your own account");
                return;
            }
            
            // Check if this is the last admin user
            if (userService.isLastAdmin(userId)) {
                redirectionUtil.urlRedirectWithMessage(request, response, "/admin/users", "error", "Cannot delete the last admin user");
                return;
            }
            
            // Attempt to delete the user
            boolean deleted = userService.deleteUser(userId);
            
            if (deleted) {
                redirectionUtil.urlRedirectWithMessage(request, response, "/admin/users", "success", "User successfully deleted");
            } else {
                redirectionUtil.urlRedirectWithMessage(request, response, "/admin/users", "error", "Failed to delete user");
            }
            
        } catch (NumberFormatException e) {
            redirectionUtil.urlRedirectWithMessage(request, response, "/admin/users", "error", "Invalid user ID format");
        }
    }

    /**
     * Redirect GET requests to POST
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}