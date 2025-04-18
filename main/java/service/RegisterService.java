package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import jakarta.servlet.http.Part;
import config.DbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ImageUtil;
import utils.PasswordUtil;
import utils.RedirectionUtil;
import utils.ValidationResult;
import utils.ValidationUtils;

public class RegisterService {

	private Connection DbConnection;
	private RedirectionUtil redirectionUtil;
	private PasswordUtil passwordUtil;
	private ImageUtil imageUtil;

	public RegisterService() {
		this.redirectionUtil = new RedirectionUtil();
		this.passwordUtil = new PasswordUtil();
		this.imageUtil = new ImageUtil();
		
		try {
			Connection dbConnection = DbConfig.getDbConnection();
			this.DbConnection = dbConnection;
		} catch (Exception e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void addUser(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	    if (DbConnection == null) {
	        redirectionUtil.redirectWithMessage(req, res, "register.jsp", "error", "No database connection");
	      
	        return;
	    }

	    String firstName = req.getParameter("firstName");
	    String lastName = req.getParameter("lastName");
	    String email = req.getParameter("email");
	    String phone = req.getParameter("phone");
	    String country = req.getParameter("country");
	    String password = req.getParameter("password");
	    String confirmPassword = req.getParameter("confirmPassword");
	    Part profilePicture = req.getPart("profilePicture");

	    ValidationResult result = ValidationUtils.verifyDetails(
	            firstName,
	            lastName,
	            email,
	            phone,
	            country,
	            password,
	            confirmPassword,
	            profilePicture);
	    if (!result.isSuccess()) {
//	        redirectionUtil.(req, res, "error", result.getMessage());
	        redirectionUtil.redirectWithMessage(req, res,"register.jsp", "error", result.getMessage());
	        return;
	    }

	    String profilePictureName = imageUtil.getImageNameFromPart(profilePicture);
	    Boolean isUploadedBoolean = imageUtil.uploadImage(profilePicture, "rootPath", "profileImages");
	    String hashedPassword = passwordUtil.hashPassword(password);

	    String insertQuery = "INSERT INTO user (first_name, last_name, email, phone, country, profile_picture, user_role, password)"
	            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement insertStmt = DbConnection.prepareStatement(insertQuery)) {
	        insertStmt.setString(1, firstName);
	        insertStmt.setString(2, lastName);
	        insertStmt.setString(3, email);
	        insertStmt.setString(4, phone);
	        insertStmt.setString(5, country);
	        insertStmt.setString(6, profilePictureName);
	        insertStmt.setString(7, "general"); // Assuming user_type is "general"
	        insertStmt.setString(8, hashedPassword);

	        int rows = insertStmt.executeUpdate();
	        if (rows > 0) {
//	            redirectionUtil.redire(req, res,"success", "User registered successfully!");
	            redirectionUtil.redirectWithMessage(req, res, "login.jsp", "success", "User registered successfully!");
	        } else {
//	            redirectionUtil.sendMessageData(req, res, "error",  "Failed to register user.");
	            redirectionUtil.redirectWithMessage(req, res, "register.jsp", "error", "Failed to register user.");

	        }
	    } catch (SQLException e) {
	        System.err.println("Error during user registration: " + e.getMessage());
	        e.printStackTrace();
//	        redirectionUtil.sendMessageData(req, res,"error", "An error occurred during registration.");
            redirectionUtil.redirectWithMessage(req, res, "register.jsp", "error", "An error occurred during registration.");

	    }
	}
}
