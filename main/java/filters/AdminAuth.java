package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import utils.RedirectionUtil;

import java.io.IOException;

/**
 * Servlet Filter implementation class AdminAuth
 * This filter protects all routes under /admin/* and ensures only users with admin role can access them.
 */
@WebFilter("/admin/*")
public class AdminAuth extends HttpFilter implements Filter {
    RedirectionUtil redirectionUtil;
    
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminAuth() {
        super();
        this.redirectionUtil = new RedirectionUtil();
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // Cleanup code if needed
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     * Check if user is logged in and has admin role, if not redirect with error message
     */
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        // Get the session, don't create one if it doesn't exist
        HttpSession session = req.getSession(false);
        
        // Check if user is logged in and has admin role
        boolean isAdmin = false;
        
        if (session != null) {
            // Check if isLoggedIn attribute exists and is true
            Object loginStatus = session.getAttribute("isLoggedIn");
            boolean isLoggedIn = (loginStatus != null && (Boolean) loginStatus);
            
            if (isLoggedIn) {
                // Check if user has admin role
                UserModel user = (UserModel) session.getAttribute("user");
                if (user != null && "admin".equalsIgnoreCase(user.getUserRole())) {
                    isAdmin = true;
                }
            }
        }
        
        // If user is logged in and is an admin, continue with the request
        if (isAdmin) {
            // Pass the request along the filter chain
            chain.doFilter(request, response);
        } else {
            // User not logged in or not an admin, redirect with error message
            redirectionUtil.urlRedirectWithMessage(req, res, "/login", "error", 
                "Access denied!");
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // Initialization code if needed
    }
}