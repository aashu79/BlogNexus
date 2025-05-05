package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class IndexController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/" })
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Get the requested URI relative to the context path
	    String requestedUri = request.getRequestURI();
	    String contextPath = request.getContextPath();

	    // Check if the requested URI matches the root route "/"
//	    if (requestedUri.equals(contextPath + "/")) {
	        // Forward to the index.jsp page
	        request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
//	    } else {
//	        // Send 404 error for any other route
//	        request.getRequestDispatcher("/WEB-INF/pages/error404.jsp").forward(request, response);
//	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
