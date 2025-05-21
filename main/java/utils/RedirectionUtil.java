package utils;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Utility class for handling page redirections and message passing in web
 * application. Provides methods for page forwarding, URL redirection, and
 * attaching messages to request/session.
 */
public class RedirectionUtil {
	private final String baseUrl = "/WEB-INF/pages/";

	/**
	 * Creates a complete URL for redirection by prepending base URL path.
	 * 
	 * @param url The target page URL/path
	 * @return Complete URL path for redirection
	 */
	public String createRedirectionUrl(String url) {
		return this.baseUrl + url;
	}

	/**
	 * Attaches a message to the request for display on the target page.
	 * 
	 * @param req           HTTP request to attach message to
	 * @param res           HTTP response (unused but kept for method signature
	 *                      consistency)
	 * @param typeOfMessage Message type identifier (e.g., "error", "success")
	 * @param message       The actual message content
	 */
	public void sendMessageData(HttpServletRequest req, HttpServletResponse res, String typeOfMessage, String message) {
		req.setAttribute(typeOfMessage, message);
	}

	/**
	 * Stores a message in the session for display across multiple requests.
	 * 
	 * @param req           HTTP request to get session from
	 * @param typeOfMessage Message type identifier (e.g., "error", "success")
	 * @param message       The actual message content
	 */
	public void setSessionMessage(HttpServletRequest req, String typeOfMessage, String message) {
		HttpSession session = req.getSession();
		session.setAttribute(typeOfMessage, message);
	}

	/**
	 * Forwards request to specified page using request dispatcher.
	 * 
	 * @param req  HTTP request to forward
	 * @param res  HTTP response to forward
	 * @param page Target page for redirection
	 * @throws IOException      If an I/O error occurs during forwarding
	 * @throws ServletException If forwarding fails due to a servlet error
	 */
	public void redirect(HttpServletRequest req, HttpServletResponse res, String page)
			throws IOException, ServletException {
		String redirectionUrl = createRedirectionUrl(page);
		req.getRequestDispatcher(redirectionUrl).forward(req, res);
	}

	/**
	 * Performs an HTTP redirect (URL redirection) to the specified page.
	 * 
	 * @param req  HTTP request (unused but kept for method signature consistency)
	 * @param res  HTTP response for redirection
	 * @param page Target page for redirection (without base URL)
	 * @throws IOException If an I/O error occurs during redirection
	 */
	public void urlRedirect(HttpServletRequest req, HttpServletResponse res, String page) throws IOException {
		res.sendRedirect(req.getContextPath() + page);
	}

	/**
	 * Forwards request to specified page with an attached message. Combines
	 * sendMessageData and redirect operations.
	 * 
	 * @param req           HTTP request to forward
	 * @param res           HTTP response to forward
	 * @param page          Target page for redirection
	 * @param typeOfMessage Message type identifier (e.g., "error", "success")
	 * @param message       The actual message content
	 * @throws IOException      If an I/O error occurs during forwarding
	 * @throws ServletException If forwarding fails due to a servlet error
	 */
	public void redirectWithMessage(HttpServletRequest req, HttpServletResponse res, String page, String typeOfMessage,
			String message) throws IOException, ServletException {
		sendMessageData(req, res, typeOfMessage, message);
		redirect(req, res, page);
	}

	/**
	 * Performs URL redirection with a message stored in the session. Use this when
	 * redirecting to a different URL while preserving messages.
	 * 
	 * @param req           HTTP request to get session from
	 * @param res           HTTP response for redirection
	 * @param page          Target page for redirection
	 * @param typeOfMessage Message type identifier (e.g., "error", "success")
	 * @param message       The actual message content
	 * @throws IOException If an I/O error occurs during redirection
	 */
	public void urlRedirectWithMessage(HttpServletRequest req, HttpServletResponse res, String page,
			String typeOfMessage, String message) throws IOException {
		setSessionMessage(req, typeOfMessage, message);
		urlRedirect(req, res, page);
	}

	/**
	 * Forwards request to specified page with a message stored in the session.
	 * Message persists across requests until explicitly removed.
	 * 
	 * @param req           HTTP request to forward
	 * @param res           HTTP response to forward
	 * @param page          Target page for redirection
	 * @param typeOfMessage Message type identifier (e.g., "error", "success")
	 * @param message       The actual message content
	 * @throws IOException      If an I/O error occurs during forwarding
	 * @throws ServletException If forwarding fails due to a servlet error
	 */
	public void redirectWithSessionMessage(HttpServletRequest req, HttpServletResponse res, String page,
			String typeOfMessage, String message) throws IOException, ServletException {
		setSessionMessage(req, typeOfMessage, message);
		redirect(req, res, page);
	}
}