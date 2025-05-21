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
 * Servlet Filter implementation class AdminAuth. Protects all routes under
 * /admin/* and ensures only users with admin roles can access them.
 */
@WebFilter("/admin/*")
public class AdminAuth extends HttpFilter implements Filter {
	private RedirectionUtil redirectionUtil;

	/**
	 * Initializes the AdminAuth filter with required utilities.
	 */
	public AdminAuth() {
		super();
		this.redirectionUtil = new RedirectionUtil();
	}

	/**
	 * @see Filter#destroy() Cleanup code if needed.
	 */
	public void destroy() {
		// Cleanup code if needed
	}

	/**
	 * Filters requests and ensures only admin users can access /admin/* routes.
	 * 
	 * @param request  The HTTP request object
	 * @param response The HTTP response object
	 * @param chain    The filter chain
	 * @throws IOException      If an I/O error occurs
	 * @throws ServletException If a servlet-specific error occurs
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// Get the session, don't create one if it doesn't exist
		HttpSession session = req.getSession(false);

		// Check if user is logged in and has the admin role
		boolean isAdmin = false;

		if (session != null) {
			// Check if isLoggedIn attribute exists and is true
			Object loginStatus = session.getAttribute("isLoggedIn");
			boolean isLoggedIn = (loginStatus != null && (Boolean) loginStatus);

			if (isLoggedIn) {
				// Check if user has an admin role
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
			// User not logged in or not an admin, redirect with an error message
			redirectionUtil.urlRedirectWithMessage(req, res, "/login", "error", "Access denied!");
		}
	}

	/**
	 * @see Filter#init(FilterConfig) Initialization code if needed.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization code if needed
	}
}