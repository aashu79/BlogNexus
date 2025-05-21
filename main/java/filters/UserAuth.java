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
import utils.RedirectionUtil;

import java.io.IOException;

/**
 * Servlet Filter implementation class UserAuth. This filter protects all routes
 * under /user/* and ensures only logged-in users can access them.
 */
@WebFilter("/user/*")
public class UserAuth extends HttpFilter implements Filter {
	private RedirectionUtil redirectionUtil;

	/**
	 * Initializes the UserAuth filter with required utilities.
	 */
	public UserAuth() {
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
	 * Filters requests and ensures only logged-in users can access /user/* routes.
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

		// Check if user is logged in
		boolean isLoggedIn = false;
		if (session != null) {
			// Check if isLoggedIn attribute exists and is true
			Object loginStatus = session.getAttribute("isLoggedIn");
			isLoggedIn = (loginStatus != null && (Boolean) loginStatus);
		}

		// If user is logged in, continue with the request
		if (isLoggedIn) {
			// Pass the request along the filter chain
			chain.doFilter(request, response);
		} else {
			// Redirect to login page with an error message
			redirectionUtil.urlRedirectWithMessage(request, response, "/login", "error", "You are not authorized.");
		}
	}

	/**
	 * @see Filter#init(FilterConfig) Initialization code if needed.
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization code if needed
	}
}