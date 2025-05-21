package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.UserModel;
import utils.ImageUtil;
import utils.PasswordUtil;
import utils.RedirectionUtil;
import utils.ValidationResult;
import utils.ValidationUtils;

public class LoginService {

	private Connection dbConnection;
	private RedirectionUtil redirectionUtil;
	private PasswordUtil passwordUtil;
	private ImageUtil imageUtil; // Added ImageUtil

	/**
	 * Initializes login service with connection and utilities
	 */
	public LoginService() {
		this.redirectionUtil = new RedirectionUtil();
		this.passwordUtil = new PasswordUtil();
		this.imageUtil = new ImageUtil(); // Initialize ImageUtil

		try {
			Connection dbConnection = DbConfig.getDbConnection();
			this.dbConnection = dbConnection;
		} catch (Exception e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Authenticates user credentials and creates session
	 * 
	 * @param req HTTP request with login parameters
	 * @param res HTTP response for redirection
	 * @throws IOException      If redirection fails
	 * @throws ServletException If request processing fails
	 */
	public void authenticateUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (dbConnection == null) {
			redirectionUtil.redirectWithMessage(req, res, "login.jsp", "error", "No database connection");
			return;
		}

		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Validate login inputs
		ValidationResult result = validateLoginInputs(email, password);
		if (!result.isSuccess()) {
			redirectionUtil.redirectWithMessage(req, res, "login.jsp", "error", result.getMessage());
			return;
		}

		// Check if user exists and password is correct
		String query = "SELECT * FROM user WHERE email = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setString(1, email);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String storedPassword = rs.getString("password");

					// Verify password
					if (passwordUtil.verifyPassword(password, storedPassword)) {
						// Create user session
						UserModel user = new UserModel();
						user.setUserId(rs.getInt("user_id"));
						user.setFirstName(rs.getString("first_name"));
						user.setLastName(rs.getString("last_name"));
						user.setEmail(rs.getString("email"));
						user.setPhone(rs.getString("phone"));
						user.setCountry(rs.getString("country"));

						// Get profile picture filename and URL
						String profilePictureFilename = rs.getString("profile_picture");
						user.setProfilePicture(profilePictureFilename); // Store the filename

						// Get and store the web URL for the profile picture
						String profilePictureUrl = imageUtil.getProfileImageUrl(profilePictureFilename);
						user.setProfilePictureUrl(profilePictureUrl); // Set the URL for web access

						user.setUserRole(rs.getString("user_role"));

						// Create session
						HttpSession session = req.getSession();
						session.setAttribute("user", user);
						session.setAttribute("isLoggedIn", true);

						// Redirect based on role
						if ("admin".equals(user.getUserRole())) {
							redirectionUtil.urlRedirectWithMessage(req, res, "/admin/dashboard", "success",
									"Welcome Admin!");
						} else {
							redirectionUtil.urlRedirectWithMessage(req, res, "/", "success", "Login successful!");
						}
					} else {
						redirectionUtil.redirectWithMessage(req, res, "login.jsp", "error",
								"Invalid email or password");
					}
				} else {
					redirectionUtil.redirectWithMessage(req, res, "login.jsp", "error", "Invalid email or password");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error during login: " + e.getMessage());
			e.printStackTrace();
			redirectionUtil.redirectWithMessage(req, res, "login.jsp", "error", "An error occurred during login");
		}
	}

	/**
	 * Validates login input fields
	 * 
	 * @param email    User email
	 * @param password User password
	 * @return ValidationResult with success status and message
	 */
	private ValidationResult validateLoginInputs(String email, String password) {
		if (email == null || email.trim().isEmpty()) {
			return new ValidationResult(false, "Email is required");
		}

		if (password == null || password.trim().isEmpty()) {
			return new ValidationResult(false, "Password is required");
		}

		// Email format validation
		if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			return new ValidationResult(false, "Invalid email format");
		}

		return new ValidationResult(true, "Validation successful");
	}

	/**
	 * Logs out user by invalidating session
	 * 
	 * @param req HTTP request with session
	 * @param res HTTP response for redirection
	 * @throws IOException      If redirection fails
	 * @throws ServletException
	 */
	public void logoutUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		redirectionUtil.redirectWithMessage(req, res, "login.jsp", "success", "You have been logged out successfully");
	}
}