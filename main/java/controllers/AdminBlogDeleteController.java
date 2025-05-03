package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import service.BlogService;
import utils.RedirectionUtil;

import java.io.IOException;

/**
 * Controller for blog deletion in admin panel
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin/blogs/delete" })
public class AdminBlogDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BlogService blogService;
    private RedirectionUtil redirectionUtil;
    
    /**
     * Initialize with blog service
     */
    public AdminBlogDeleteController() {
        super();
        this.blogService = new BlogService();
        this.redirectionUtil = new RedirectionUtil();
    }
    
    /**
     * Handles GET requests (redirects to listing)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin/blogs");
    }
    
    /**
     * Processes blog deletion request
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verify admin access
        HttpSession session = request.getSession(false);
  
        
        UserModel currentUser = (UserModel) session.getAttribute("user");
  
        
        // Process deletion using existing service method
        String blogId = request.getParameter("id");
        if (blogId != null && !blogId.isEmpty()) {
            try {
             
                blogService.deleteBlog(request, response);
            } catch (Exception e) {
                System.err.println("Error deleting blog: " + e.getMessage());
                e.printStackTrace();
                redirectionUtil.urlRedirectWithMessage(request, response, "/admin/blogs", "error", "Failed to delete blog: " + e.getMessage());
            }
        } else {
            redirectionUtil.urlRedirectWithMessage(request, response, "/admin/blogs", "error", "Blog ID is required");
        }
    }
}