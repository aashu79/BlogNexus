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
import java.util.List;

/**
 * Controller for user profile operations.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user/profile" })
public class ProfileController extends HttpServlet {
   
    private BlogService blogService;
       
    /**
     * Initializes the controller with required services.
     */
    public ProfileController() {
        super();
        this.blogService = new BlogService();
    }

    /**
     * Displays user profile with their blogs.
     * @param request HTTP request
     * @param response HTTP response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // UserAuth filter already ensures the user is logged in
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        
        // Get user's blogs
        List<BlogModel> userBlogs = blogService.getBlogsByUserId(user.getUserId());
        request.setAttribute("userBlogs", userBlogs);
        
        // Forward to profile page
        request.getRequestDispatcher("/WEB-INF/pages/profile_page.jsp").forward(request, response);
    }

    /**
     * Handles POST requests by redirecting to GET.
     * @param request HTTP request
     * @param response HTTP response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Simply redirect to GET
        response.sendRedirect(request.getRequestURI());
    }
}