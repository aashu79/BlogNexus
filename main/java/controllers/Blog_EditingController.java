package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BlogModel;
import model.UserModel;
import service.BlogService;
import utils.RedirectionUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for blog editing operations.
 * Handles displaying the edit form and processing blog updates.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/user/blog/edit/*"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,     // 1 MB
    maxFileSize = 1024 * 1024 * 10,      // 10 MB
    maxRequestSize = 1024 * 1024 * 50    // 50 MB
)
public class Blog_EditingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BlogService blogService;
    private RedirectionUtil redirectionUtil;
       
    /**
     * Initialize controller with required services
     */
    public Blog_EditingController() {
        super();
        this.blogService = new BlogService();
        this.redirectionUtil = new RedirectionUtil();
    }

    /**
     * Handles GET requests for displaying blog edit form
     * URL format: /user/blog/edit/{blogId}
     * 
     * @param request HTTP request with blog ID in path
     * @param response HTTP response
     * @throws ServletException If servlet operation fails
     * @throws IOException If I/O operation fails
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract blog ID from the URL path
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null || pathInfo.equals("/")) {
            // No blog ID provided, redirect to profile
            response.sendRedirect(request.getContextPath() + "/user/profile");
            return;
        }
        
        // Remove the leading slash and parse the ID
        String blogIdStr = pathInfo.substring(1);
        System.out.println("Blog ID from path: " + blogIdStr);
        
        try {
            // Convert string ID to integer
            int blogId = Integer.parseInt(blogIdStr);
            
            // Get user from session to check authorization
            HttpSession session = request.getSession();
            UserModel user = (UserModel) session.getAttribute("user");
            
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            
            // Fetch blog details
            BlogModel blog = blogService.getBlogById(blogId);
            
            if (blog == null) {
                // Blog not found, redirect to home
                redirectionUtil.urlRedirectWithMessage(request, response, 
                    "/", "error", "Blog not found");
                return;
            }
            
            // Check if user is authorized to edit this blog (owner or admin)
            if (blog.getCreatedBy() != user.getUserId() && !"admin".equals(user.getUserRole())) {
                // Not authorized, redirect to profile with error message
                redirectionUtil.urlRedirectWithMessage(request, response, 
                    "/user/profile", "error", "You are not authorized to edit this blog");
                return;
            }
            
            // Set blog in request attribute to prefill the form
            request.setAttribute("blog", blog);
            
            // Forward to edit page
            request.getRequestDispatcher("/WEB-INF/pages/blog_editing.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            // Invalid blog ID format, redirect to profile
            redirectionUtil.urlRedirectWithMessage(request, response, 
                "/user/profile", "error", "Invalid blog ID format");
        } catch (Exception e) {
            // Other errors
            System.err.println("Error loading blog for editing: " + e.getMessage());
            e.printStackTrace();
            redirectionUtil.urlRedirectWithMessage(request, response, 
                "/", "error", "An error occurred while loading the blog");
        }
    }

    /**
     * Handles POST requests for updating blogs
     * This method handles form submissions from the edit page.
     * 
     * @param request HTTP request with blog data
     * @param response HTTP response
     * @throws ServletException If servlet operation fails
     * @throws IOException If I/O operation fails
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Log all parameters for debugging
        System.out.println("=== DEBUG: Received POST request for blog update ===");
        Map<String, String[]> params = request.getParameterMap();
        params.forEach((key, values) -> {
            System.out.println(key + ": " + (values != null && values.length > 0 ? values[0] : "null"));
        });
        
        // Get blog ID from request parameters
        String blogIdParam = request.getParameter("id");
        System.out.println("Blog ID from parameter: " + blogIdParam);
        
        // If not in parameters, try to get from path info
        if (blogIdParam == null || blogIdParam.trim().isEmpty()) {
            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.length() > 1) {
                blogIdParam = pathInfo.substring(1);
                System.out.println("Blog ID from path: " + blogIdParam);
            }
        }
        
        // If still no blog ID, redirect with error
        if (blogIdParam == null || blogIdParam.trim().isEmpty()) {
            redirectionUtil.urlRedirectWithMessage(request, response, 
                "/user/profile", "error", "Blog ID is required for updates");
            return;
        }
        
        // Use a wrapper to ensure the ID parameter is available to the service
        ParameterWrapper wrappedRequest = new ParameterWrapper(request);
        wrappedRequest.setParameter("id", blogIdParam);
        
        // Process the update with the wrapped request
        blogService.updateBlog(wrappedRequest, response);
    }
    
    /**
     * Helper class to add parameters to request
     */
    private static class ParameterWrapper extends HttpServletRequestWrapper {
        private final Map<String, String[]> customParameters;

        public ParameterWrapper(HttpServletRequest request) {
            super(request);
            this.customParameters = new HashMap<>();
            
            // Copy existing parameters
            request.getParameterMap().forEach((key, values) -> {
                customParameters.put(key, values);
            });
        }

        public void setParameter(String name, String value) {
            customParameters.put(name, new String[] { value });
        }

        @Override
        public String getParameter(String name) {
            String[] values = getParameterValues(name);
            if (values == null || values.length == 0) {
                return null;
            }
            return values[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return Collections.unmodifiableMap(customParameters);
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return Collections.enumeration(customParameters.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return customParameters.get(name);
        }
    }
}