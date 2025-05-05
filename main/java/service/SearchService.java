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
import utils.ImageUtil;

/**
 * Service class for search-related operations
 */
public class SearchService {
    
    private Connection dbConnection;
    private ImageUtil imageUtil;
    
    /**
     * Initialize the service with required dependencies
     */
    public SearchService() {
        this.imageUtil = new ImageUtil();
        
        try {
            this.dbConnection = DbConfig.getDbConnection();
        } catch (Exception e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Search blogs by keyword with author details
     * 
     * @param query The search query
     * @return Map containing blogs and author details
     */
    public Map<String, Object> searchBlogs(String query) {
        List<BlogModel> blogs = new ArrayList<>();
        Map<Integer, Map<String, String>> authorDetails = new HashMap<>();
        
        // Prepare the search query - search in title and content
        String searchQuery = "SELECT b.*, u.first_name, u.last_name, u.profile_picture, u.user_role " +
                            "FROM blog b " +
                            "JOIN user u ON b.created_by = u.user_id " +
                            "WHERE LOWER(b.title) LIKE ? OR LOWER(b.content) LIKE ? OR LOWER(b.genre) LIKE ? " +
                            "ORDER BY b.created_at DESC";
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(searchQuery)) {
            // Add wildcards for SQL LIKE
            String searchParam = "%" + query.toLowerCase() + "%";
            stmt.setString(1, searchParam);
            stmt.setString(2, searchParam);
            stmt.setString(3, searchParam);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    BlogModel blog = new BlogModel();
                    blog.setBlogId(rs.getInt("blog_id"));
                    blog.setTitle(rs.getString("title"));
                    
                    // Get content excerpt
                    String content = rs.getString("content");
                    String excerpt = content.length() > 150 ? content.substring(0, 147) + "..." : content;
                    blog.setContent(excerpt);
                    
                    // Get images
                    String thumbnailName = rs.getString("thumbnail");
                    blog.setThumbnail(thumbnailName);
                    blog.setThumbnailUrl(imageUtil.getThumbnailImageUrl(thumbnailName));
                    
                    String imageName = rs.getString("image");
                    blog.setImage(imageName);
                    blog.setImageUrl(imageUtil.getBlogImageUrl(imageName));
                    
                    blog.setGenre(rs.getString("genre"));
                    blog.setCreatedBy(rs.getInt("created_by"));
                    
                    // Author name
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    blog.setAuthorName(firstName + " " + lastName);
                    
                    // Timestamp handling
                    try {
                        java.sql.Timestamp timestamp = rs.getTimestamp("created_at");
                        if (timestamp != null) {
                            LocalDateTime createdAt = timestamp.toLocalDateTime();
                            blog.setCreatedAt(createdAt);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error parsing timestamp: " + e.getMessage());
                    }
                    
                    // Author details
                    Map<String, String> details = new HashMap<>();
                    details.put("authorRole", rs.getString("user_role"));
                    
                    // Profile picture
                    String profilePic = rs.getString("profile_picture");
                    if (profilePic != null && !profilePic.isEmpty()) {
                        details.put("profilePicture", imageUtil.getProfileImageUrl(profilePic));
                    } else {
                        details.put("profilePicture", "");
                    }
                    
                    // Format date and calculate read time
                    if (blog.getCreatedAt() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                        details.put("formattedDate", blog.getCreatedAt().format(formatter));
                    } else {
                        details.put("formattedDate", "Recent post");
                    }
                    
                    int wordCount = content.split("\\s+").length;
                    int readMinutes = Math.max(1, wordCount / 200);
                    details.put("readTime", readMinutes + " min read");
                    
                    blogs.add(blog);
                    authorDetails.put(blog.getBlogId(), details);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching blogs: " + e.getMessage());
            e.printStackTrace();
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("blogs", blogs);
        result.put("authorDetails", authorDetails);
        return result;
    }
}