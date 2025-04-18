package utils;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectionUtil {
	private  final String baseUrl = "/WEB-INF/pages/";
	
	
	public String createRedirectionUrl(String url) {
		return this.baseUrl + url;
	}
	
	public  void sendMessageData(HttpServletRequest req, HttpServletResponse res,String typeOfMessage, String message) {
		req.setAttribute(typeOfMessage, message);
		
	}

	public  void redirect(HttpServletRequest req, HttpServletResponse res, String page) throws IOException, ServletException {
		String redirectionUrl = createRedirectionUrl(page);
		req.getRequestDispatcher(redirectionUrl).forward(req, res);
		
	}
	
	
	public  void redirectWithMessage(HttpServletRequest req, HttpServletResponse res, String page, String typeOfMessage, String message) throws IOException, ServletException {
	
		sendMessageData(req, res,typeOfMessage, message);
		redirect(req, res, page);
	}
	
	
}
