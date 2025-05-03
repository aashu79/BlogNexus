package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.DbConfig;
import model.BlogModel;
import model.UserModel;
import utils.ImageUtil;

/**
 * Service class for admin dashboard data
 * Provides statistics and recent data for the admin dashboard
 */
public class DashboardService {

    private Connection dbConnection;
    private ImageUtil imageUtil;
    
    /**
     * Initialize service with database connection and utilities
     */
    public DashboardService() {
        this.imageUtil = new ImageUtil();
        try {
            this.dbConnection = DbConfig.getDbConnection();
        } catch (Exception e) {
            System.err.println("Database connection error in DashboardService: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Get dashboard summary statistics
     * @return Map containing counts of users, blogs, and comments
     */
    public Map<String, Integer> getDashboardStats() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("userCount", 0);
        stats.put("blogCount", 0);
        stats.put("commentCount", 0);
        
        if (dbConnection == null) {
            return stats;
        }
        
        // Get user count
        try (PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as count FROM user")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.put("userCount", rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching user count: " + e.getMessage());
        }
        
        // Get blog count
        try (PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as count FROM blog")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.put("blogCount", rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching blog count: " + e.getMessage());
        }
        
        // Get comment count (if comments table exists)
        try (PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as count FROM comment")) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    stats.put("commentCount", rs.getInt("count"));
                }
            }
        } catch (SQLException e) {
            // Comment feature might not be implemented yet, so just log without error
            System.out.println("Comment table may not exist yet: " + e.getMessage());
        }
        
        return stats;
    }
    
    /**
     * Get list of recent blogs with author information
     * @param limit Number of blogs to retrieve
     * @return List of recent BlogModel objects
     */
    public List<BlogModel> getRecentBlogs(int limit) {
        List<BlogModel> blogs = new ArrayList<>();
        
        if (dbConnection == null) {
            return blogs;
        }
        
        String query = "SELECT b.*, u.first_name, u.last_name FROM blog b " +
                      "JOIN user u ON b.created_by = u.user_id " +
                      "ORDER BY b.created_at DESC LIMIT ?";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, limit);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BlogModel blog = new BlogModel();
                    blog.setBlogId(rs.getInt("blog_id"));
                    blog.setTitle(rs.getString("title"));
                    blog.setContent(rs.getString("content"));
                    blog.setThumbnail(rs.getString("thumbnail"));
                    blog.setImage(rs.getString("image"));
                    blog.setGenre(rs.getString("genre"));
                    blog.setCreatedBy(rs.getInt("created_by"));
                    
                    // Parse created_at from string to LocalDateTime
                    String createdAtStr = rs.getString("created_at");
                    if (createdAtStr != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);
                        blog.setCreatedAt(createdAt);
                    }
                    
                    // Set URLs for images
                    if (blog.getThumbnail() != null && !blog.getThumbnail().isEmpty()) {
                        blog.setThumbnailUrl(imageUtil.getThumbnailImageUrl(blog.getThumbnail()));
                    }
                    if (blog.getImage() != null && !blog.getImage().isEmpty()) {
                        blog.setImageUrl(imageUtil.getBlogImageUrl(blog.getImage()));
                    }
                    
                    // Add author name as a transient property
                    blog.setAuthorName(rs.getString("first_name") + " " + rs.getString("last_name"));
                    
                    blogs.add(blog);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching recent blogs: " + e.getMessage());
        }
        
        return blogs;
    }
    
    /**
     * Get list of recent users
     * @param limit Number of users to retrieve
     * @return List of recent UserModel objects
     */
    public List<UserModel> getRecentUsers(int limit) {
        List<UserModel> users = new ArrayList<>();
        
        if (dbConnection == null) {
            return users;
        }
        
        String query = "SELECT * FROM user ORDER BY user_id DESC LIMIT ?";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setInt(1, limit);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
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
                    
                    // Add registration date if it exists in your schema
                    // user.setRegistrationDate(rs.getTimestamp("registration_date"));
                    
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching recent users: " + e.getMessage());
        }
        
        return users;
    }
    
    /**
     * Get the current system date and time formatted for display
     * @return Formatted date and time string
     */
    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}