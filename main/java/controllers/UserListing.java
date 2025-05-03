package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserModel;
import service.UserService;

import java.io.IOException;
import java.util.List;

/**
 * Controller for user listing in admin panel
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/users" })
public class UserListing extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
       
    /**
     * Initialize controller with service
     */
    public UserListing() {
        super();
        this.userService = new UserService();
    }

    /**
     * Handle GET requests to display user listing
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verify admin access
        HttpSession session = request.getSession(false);
 
        
        UserModel currentUser = (UserModel) session.getAttribute("user");
   
        // Get all users
        List<UserModel> users = userService.getAllUsers();
        
        // Pass data to JSP
        request.setAttribute("users", users);
        request.setAttribute("currentUser", currentUser);
        request.setAttribute("currentDateTime", java.time.LocalDateTime.now().toString());
        
        request.getRequestDispatcher("/WEB-INF/pages/user_listing.jsp").forward(request, response);
    }

    /**
     * Handle POST requests
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}