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
import jakarta.servlet.http.Part;
import model.UserModel;
import utils.ImageUtil;
import utils.PasswordUtil;
import utils.RedirectionUtil;
import utils.ValidationResult;

public class ProfileUpdatingService {

    private Connection dbConnection;
    private RedirectionUtil redirectionUtil;
    private PasswordUtil passwordUtil;
    private ImageUtil imageUtil;

    /**
     * Initializes profile updating service with connection and utilities
     */
    public ProfileUpdatingService() {
        this.redirectionUtil = new RedirectionUtil();
        this.passwordUtil = new PasswordUtil();
        this.imageUtil = new ImageUtil();
        
        try {
            Connection dbConnection = DbConfig.getDbConnection();
            this.dbConnection = dbConnection;
        } catch (Exception e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Updates the user profile with form data
     * @param req HTTP request with profile parameters
     * @param res HTTP response for redirection
     * @throws IOException If redirection fails
     * @throws ServletException If request processing fails
     */
    public void updateProfile(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Check database connection
        if (dbConnection == null) {
            redirectionUtil.redirectWithMessage(req, res, "user/profile/update", "error", "No database connection");
            return;
        }

        // Check if user is logged in
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            redirectionUtil.urlRedirectWithMessage(req, res, "/login.jsp", "error", "Please login to continue");
            return;
        }

        // Get current user info from session
        UserModel currentUser = (UserModel) session.getAttribute("user");
        int userId = currentUser.getUserId();

        // Get form data
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String country = req.getParameter("country");
        
        // Password fields (optional)
        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        // Get profile picture information
        String currentProfilePicture = req.getParameter("currentProfilePicture");
        Part profilePicturePart = null;
        try {
            profilePicturePart = req.getPart("profilePicture");
        } catch (Exception e) {
            System.err.println("Error processing file upload: " + e.getMessage());
        }

        // Validate profile inputs
        ValidationResult validationResult = validateProfileInputs(firstName, lastName, email, country);
        if (!validationResult.isSuccess()) {
            redirectionUtil.redirectWithMessage(req, res, "user/profile/update", "error", validationResult.getMessage());
            return;
        }

        // Check if user is trying to change password
        boolean isChangingPassword = currentPassword != null && !currentPassword.trim().isEmpty();
        if (isChangingPassword) {
            // Validate password change
            ValidationResult passwordResult = validatePasswordChange(currentPassword, newPassword, confirmPassword, userId);
            if (!passwordResult.isSuccess()) {
                redirectionUtil.redirectWithMessage(req, res, "user/profile/update", "error", passwordResult.getMessage());
                return;
            }
        }

        try {
            // Start transaction
            dbConnection.setAutoCommit(false);
            
            // Handle profile picture
            String profilePicturePath = currentProfilePicture;
            
            // Check if a new image is being uploaded
            if (profilePicturePart != null && profilePicturePart.getSize() > 0) {
                // Upload new profile picture using the ImageUtil method
                boolean uploadSuccessful = imageUtil.uploadProfileImage(profilePicturePart);
                
                if (uploadSuccessful) {
                    // Get the new filename from the part
                    String newProfilePicture = imageUtil.getImageNameFromPart(profilePicturePart);
                    profilePicturePath = newProfilePicture;
                    
                    // Delete old picture if different
                    if (currentProfilePicture != null && !currentProfilePicture.isEmpty() 
                            && !currentProfilePicture.equals(newProfilePicture)) {
                        imageUtil.deleteProfileImage(currentProfilePicture);
                    }
                }
            } else if (currentProfilePicture == null || currentProfilePicture.trim().isEmpty()) {
                // User removed profile picture
                if (currentUser.getProfilePicture() != null && !currentUser.getProfilePicture().isEmpty()) {
                    imageUtil.deleteProfileImage(currentUser.getProfilePicture());
                }
                profilePicturePath = null;
            }
            
            // Update user information in database
            updateUserInfo(userId, firstName, lastName, email, phone, country, profilePicturePath);
            
            // Update password if requested
            if (isChangingPassword) {
                updatePassword(userId, newPassword);
            }
            
            // Commit all changes
            dbConnection.commit();
            
            // Update user model in session
            UserModel updatedUser = getUserById(userId);
            if (updatedUser != null) {
                session.setAttribute("user", updatedUser);
            }
            
            // Redirect with success message
            redirectionUtil.redirectWithMessage(req, res, "user/profile/update", "success", 
                "Profile updated successfully" + (isChangingPassword ? " including password" : ""));
            
        } catch (SQLException e) {
            // Roll back transaction on error
            try {
                dbConnection.rollback();
            } catch (SQLException ex) {
                System.err.println("Failed to rollback transaction: " + ex.getMessage());
            }
            
            System.err.println("Error updating profile: " + e.getMessage());
            e.printStackTrace();
            redirectionUtil.redirectWithMessage(req, res, "user/profile/update", "error", 
                "An error occurred while updating your profile");
        } finally {
            // Reset auto-commit
            try {
                dbConnection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Failed to reset auto-commit: " + e.getMessage());
            }
        }
    }
    
    /**
     * Validates basic profile input fields
     * @param firstName User's first name
     * @param lastName User's last name
     * @param email User's email
     * @param country User's country
     * @return ValidationResult with success status and message
     */
    private ValidationResult validateProfileInputs(String firstName, String lastName, String email, String country) {
        // Check required fields
        if (firstName == null || firstName.trim().isEmpty()) {
            return new ValidationResult(false, "First name is required");
        }
        
        if (lastName == null || lastName.trim().isEmpty()) {
            return new ValidationResult(false, "Last name is required");
        }
        
        if (email == null || email.trim().isEmpty()) {
            return new ValidationResult(false, "Email is required");
        }
        
        // Email format validation
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return new ValidationResult(false, "Invalid email format");
        }
        
        if (country == null || country.trim().isEmpty()) {
            return new ValidationResult(false, "Country is required");
        }
        
        return new ValidationResult(true, "Validation successful");
    }
    
    /**
     * Validates password change inputs and checks current password
     * @param currentPassword User's current password
     * @param newPassword User's new password
     * @param confirmPassword Confirmation of new password
     * @param userId User ID for database verification
     * @return ValidationResult with success status and message
     */
    private ValidationResult validatePasswordChange(String currentPassword, String newPassword, 
                                                  String confirmPassword, int userId) {
        // Check required fields for password change
        if (currentPassword == null || currentPassword.trim().isEmpty()) {
            return new ValidationResult(false, "Current password is required to change password");
        }
        
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return new ValidationResult(false, "New password is required");
        }
        
        if (confirmPassword == null || !confirmPassword.equals(newPassword)) {
            return new ValidationResult(false, "New passwords do not match");
        }
        
        // Password strength check
        if (newPassword.length() < 8) {
            return new ValidationResult(false, "Password must be at least 8 characters long");
        }
        
        // Verify current password matches in database
        if (!verifyCurrentPassword(userId, currentPassword)) {
            return new ValidationResult(false, "Current password is incorrect");
        }
        
        return new ValidationResult(true, "Validation successful");
    }
    
    /**
     * Verifies the user's current password against database
     * @param userId User ID
     * @param currentPassword Password to verify
     * @return True if password matches, false otherwise
     */
    private boolean verifyCurrentPassword(int userId, String currentPassword) {
        String query = "SELECT password FROM user WHERE user_id = ?";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return passwordUtil.verifyPassword(currentPassword, storedPassword);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error verifying password: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Updates user information in database
     * @param userId User ID to update
     * @param firstName New first name
     * @param lastName New last name
     * @param email New email
     * @param phone New phone
     * @param country New country
     * @param profilePicture New profile picture path
     * @throws SQLException If database operation fails
     */
    private void updateUserInfo(int userId, String firstName, String lastName, String email, 
                              String phone, String country, String profilePicture) throws SQLException {
        String query = "UPDATE user SET first_name = ?, last_name = ?, email = ?, " +
                      "phone = ?, country = ?, profile_picture = ? WHERE user_id = ?";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, country);
            stmt.setString(6, profilePicture);
            stmt.setInt(7, userId);
            
            stmt.executeUpdate();
        }
    }
    
    /**
     * Updates user password in database
     * @param userId User ID to update
     * @param newPassword New password to hash and store
     * @throws SQLException If database operation fails
     */
    private void updatePassword(int userId, String newPassword) throws SQLException {
        // Hash password before storing
        String hashedPassword = passwordUtil.hashPassword(newPassword);
        String query = "UPDATE user SET password = ? WHERE user_id = ?";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, hashedPassword);
            stmt.setInt(2, userId);
            
            stmt.executeUpdate();
        }
    }
    
    /**
     * Retrieves user by ID from database
     * @param userId User ID to retrieve
     * @return UserModel with updated information or null if not found
     */
    private UserModel getUserById(int userId) {
        String query = "SELECT * FROM user WHERE user_id = ?";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
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
                    
                    // Get and store the web URL for the profile picture using existing method
                    if (profilePictureFilename != null && !profilePictureFilename.isEmpty()) {
                        String profilePictureUrl = imageUtil.getProfileImageUrl(profilePictureFilename);
                        user.setProfilePictureUrl(profilePictureUrl); // Set the URL for web access
                    }
                    
                    user.setUserRole(rs.getString("user_role"));
                    
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
        
        return null;
    }
}