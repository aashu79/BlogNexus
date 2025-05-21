package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import config.DbConfig;
import model.UserModel;
import utils.ImageUtil;
import utils.PasswordUtil;
import utils.RedirectionUtil;
import utils.ValidationResult;
import utils.ValidationUtils;

/**
 * Service class for user management operations
 */
public class UserService {

	private Connection dbConnection;
	private ImageUtil imageUtil;
	private PasswordUtil passwordUtil;
	private RedirectionUtil redirectionUtil;

	/**
	 * Constructor - initializes database connection and utilities
	 */
	public UserService() {
		this.imageUtil = new ImageUtil();
		this.passwordUtil = new PasswordUtil();
		this.redirectionUtil = new RedirectionUtil();
		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (Exception e) {
			System.err.println("Database connection error in UserService: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Get all users from the database
	 * 
	 * @return List of UserModel objects
	 */
	public List<UserModel> getAllUsers() {
		List<UserModel> users = new ArrayList<>();

		if (dbConnection == null) {
			return users;
		}

		String query = "SELECT * FROM user ORDER BY user_id ASC";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				UserModel user = mapResultSetToUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			System.err.println("Error fetching users: " + e.getMessage());
		}

		return users;
	}

	/**
	 * Get a user by ID
	 * 
	 * @param userId User ID to find
	 * @return UserModel if found, null otherwise
	 */
	public UserModel getUserById(int userId) {
		if (dbConnection == null) {
			return null;
		}

		String query = "SELECT * FROM user WHERE user_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return mapResultSetToUser(rs);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error fetching user by ID: " + e.getMessage());
		}

		return null;
	}

	/**
	 * Check if email already exists in the database
	 * 
	 * @param email Email to check
	 * @return true if email exists, false otherwise
	 */
	public boolean isEmailExists(String email) {
		if (dbConnection == null) {
			return false;
		}

		String query = "SELECT COUNT(*) as count FROM user WHERE email = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setString(1, email);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("count") > 0;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error checking email existence: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Create a new user from HTTP request parameters
	 * 
	 * @param req HTTP request containing form data
	 * @param res HTTP response for redirect
	 * @throws ServletException If file upload fails
	 * @throws IOException      If redirect fails
	 */
	public void createUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (dbConnection == null) {
			System.err.println("No database connection available");
			redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error", "Database connection failed");
			return;
		}

		// Get form parameters
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String country = req.getParameter("country");
		String role = req.getParameter("role");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		Part profilePicture = req.getPart("profilePicture");

		// Check if email already exists first (since this is not checked in
		// verifyDetails)
		if (isEmailExists(email)) {
			redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error",
					"Email address already exists");
			return;
		}

		// Validate user input using existing verifyDetails method
		ValidationResult validationResult = ValidationUtils.verifyDetails(firstName, lastName, email, phone, country,
				password, confirmPassword, profilePicture);

		if (!validationResult.isSuccess()) {
			System.err.println("Validation failed: " + validationResult.getMessage());
			redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error",
					validationResult.getMessage());
			return;
		}

		// Validate role (not included in verifyDetails)
		if (ValidationUtils.isNullOrEmpty(role) || (!role.equals("admin") && !role.equals("general"))) {
			redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error", "Invalid user role");
			return;
		}

		// Handle profile picture upload
		String profilePictureName = "";
		if (profilePicture != null && profilePicture.getSize() > 0) {
			// Get file name before upload
			profilePictureName = imageUtil.getImageNameFromPart(profilePicture);

			// Upload the image
			boolean isUploaded = imageUtil.uploadProfileImage(profilePicture);
			if (!isUploaded) {
				System.err.println("Failed to upload profile image for user: " + email);
				redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error",
						"Failed to upload profile picture");
				return;
			}
		}

		// Hash password
		String hashedPassword = passwordUtil.hashPassword(password);

		// Insert user into database
		String insertQuery = "INSERT INTO user (first_name, last_name, email, phone, country, profile_picture, user_role, password) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = dbConnection.prepareStatement(insertQuery)) {
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, email);
			stmt.setString(4, phone);
			stmt.setString(5, country);
			stmt.setString(6, profilePictureName);
			stmt.setString(7, role);
			stmt.setString(8, hashedPassword);

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("User created successfully: " + email);
				redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users", "success",
						"User created successfully!");
			} else {
				System.err.println("Failed to create user: " + email);
				redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error", "Failed to create user");

				// Clean up uploaded image if user creation failed
				if (!profilePictureName.isEmpty()) {
					imageUtil.deleteProfileImage(profilePictureName);
				}
			}
		} catch (SQLException e) {
			System.err.println("SQL error creating user: " + e.getMessage());
			redirectionUtil.urlRedirectWithMessage(req, res, "/admin/users/add", "error",
					"Database error: " + e.getMessage());

			// Clean up uploaded image if user creation failed
			if (!profilePictureName.isEmpty()) {
				imageUtil.deleteProfileImage(profilePictureName);
			}
		}
	}

	/**
	 * Delete a user by ID
	 * 
	 * @param userId ID of the user to delete
	 * @return true if successful, false otherwise
	 */
	public boolean deleteUser(int userId) {
		if (dbConnection == null) {
			return false;
		}

		// Get user to retrieve profile picture before deletion
		UserModel user = getUserById(userId);
		if (user == null) {
			return false;
		}

		// Try to delete the user
		String query = "DELETE FROM user WHERE user_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, userId);

			int rowsAffected = stmt.executeUpdate();

			// Delete profile picture if user deletion was successful
			if (rowsAffected > 0 && user.getProfilePicture() != null && !user.getProfilePicture().isEmpty()) {
				imageUtil.deleteProfileImage(user.getProfilePicture());
			}

			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error deleting user: " + e.getMessage());
			return false;
		}
	}

	/**
	 * Check if a user is the last admin
	 * 
	 * @param userId ID of user to check
	 * @return true if this is the last admin user, false otherwise
	 */
	public boolean isLastAdmin(int userId) {
		if (dbConnection == null) {
			return true; // Safer to prevent deletion
		}

		// First check if the user is an admin
		String checkAdminQuery = "SELECT user_role FROM user WHERE user_id = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(checkAdminQuery)) {
			stmt.setInt(1, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String userRole = rs.getString("user_role");
					if (!"admin".equals(userRole)) {
						return false; // Not an admin, so not the last admin
					}
				} else {
					return false; // User not found
				}
			}
		} catch (SQLException e) {
			System.err.println("Error checking if user is admin: " + e.getMessage());
			return true; // Safer to prevent deletion
		}

		// Count how many admins exist
		String countAdminsQuery = "SELECT COUNT(*) as admin_count FROM user WHERE user_role = 'admin'";
		try (PreparedStatement stmt = dbConnection.prepareStatement(countAdminsQuery);
				ResultSet rs = stmt.executeQuery()) {

			if (rs.next()) {
				int adminCount = rs.getInt("admin_count");
				return adminCount <= 1; // Last admin if count is 1 or less
			}
		} catch (SQLException e) {
			System.err.println("Error counting admin users: " + e.getMessage());
		}

		return true; // Safer to prevent deletion
	}

	/**
	 * Helper method to map a ResultSet row to a UserModel object
	 * 
	 * @param rs ResultSet positioned at the row to map
	 * @return Populated UserModel
	 * @throws SQLException if database access error occurs
	 */
	private UserModel mapResultSetToUser(ResultSet rs) throws SQLException {
		UserModel user = new UserModel();
		user.setUserId(rs.getInt("user_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setCountry(rs.getString("country"));
		user.setUserRole(rs.getString("user_role"));

		String profilePicture = rs.getString("profile_picture");
		user.setProfilePicture(profilePicture);

		if (profilePicture != null && !profilePicture.isEmpty()) {
			user.setProfilePictureUrl(imageUtil.getProfileImageUrl(profilePicture));
		}

		return user;
	}
}