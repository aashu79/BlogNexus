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
 * Service class for category-related operations
 * @author Aashu Kumar Sah
 */
public class CategoryService {

	private Connection dbConnection;
	private ImageUtil imageUtil;

	/**
	 * Initialize the service with required dependencies
	 */
	public CategoryService() {
		this.imageUtil = new ImageUtil();

		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (Exception e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Get blogs by category with author details
	 * 
	 * @param category The category to filter by
	 * @return Map containing blogs and author details
	 */
	public Map<String, Object> getBlogsByCategory(String category) {
		List<BlogModel> blogs = new ArrayList<>();
		Map<Integer, Map<String, String>> authorDetails = new HashMap<>();

		String query = "SELECT b.*, u.first_name, u.last_name, u.profile_picture, u.user_role " + "FROM blog b "
				+ "JOIN user u ON b.created_by = u.user_id " + "WHERE b.genre = ? " + "ORDER BY b.created_at DESC";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setString(1, category);

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
			System.err.println("Error retrieving blogs by category: " + e.getMessage());
			e.printStackTrace();
		}

		Map<String, Object> result = new HashMap<>();
		result.put("blogs", blogs);
		result.put("authorDetails", authorDetails);
		return result;
	}

	/**
	 * Get category statistics
	 * 
	 * @param category The category to get stats for
	 * @return Map containing category statistics
	 */
//    public Map<String, Integer> getCategoryStats(String category) {
//        Map<String, Integer> stats = new HashMap<>();
//        stats.put("blogCount", 0);
//        stats.put("authorCount", 0);
//        stats.put("viewCount", 0);
//        
//        // Count blogs in category
//        String blogCountQuery = "SELECT COUNT(*) as count FROM blog WHERE genre = ?";
//        
//        // Count unique authors in category
//        String authorCountQuery = "SELECT COUNT(DISTINCT created_by) as count FROM blog WHERE genre = ?";
//        
//        // Sum views for category (assuming a view_count column or related table)
//        // If you don't have view tracking, you can remove this or modify accordingly
//        String viewCountQuery = "SELECT SUM(view_count) as count FROM blog WHERE genre = ?";
//        
//        try {
//            // Get blog count
//            try (PreparedStatement stmt = dbConnection.prepareStatement(blogCountQuery)) {
//                stmt.setString(1, category);
//                try (ResultSet rs = stmt.executeQuery()) {
//                    if (rs.next()) {
//                        stats.put("blogCount", rs.getInt("count"));
//                    }
//                }
//            }
//            
//            // Get author count
//            try (PreparedStatement stmt = dbConnection.prepareStatement(authorCountQuery)) {
//                stmt.setString(1, category);
//                try (ResultSet rs = stmt.executeQuery()) {
//                    if (rs.next()) {
//                        stats.put("authorCount", rs.getInt("count"));
//                    }
//                }
//            }
//            
//            // Get view count - modify this if you have a different way to track views
//            // Note: This assumes you have a view_count column in your blog table
//            try (PreparedStatement stmt = dbConnection.prepareStatement(viewCountQuery)) {
//                stmt.setString(1, category);
//                try (ResultSet rs = stmt.executeQuery()) {
//                    if (rs.next()) {
//                        int viewCount = rs.getInt("count");
//                        // If null (no views yet), use 0
//                        if (rs.wasNull()) {
//                            viewCount = 0;
//                        }
//                        stats.put("viewCount", viewCount);
//                    }
//                }
//            } catch (SQLException e) {
//                // If view_count column doesn't exist, use a default value
//                stats.put("viewCount", stats.get("blogCount") * 50); // Approximate view count
//            }
//            
//        } catch (SQLException e) {
//            System.err.println("Error retrieving category stats: " + e.getMessage());
//            e.printStackTrace();
//        }
//        
//        return stats;
//    }

	/**
	 * Get category description
	 * 
	 * @param category The category
	 * @return Description for the category
	 */
	public String getCategoryDescription(String category) {
		// You could store these in a database, but here's a simple map approach
		Map<String, String> descriptions = new HashMap<>();
		descriptions.put("Technology", "Explore the latest innovations and digital trends shaping our world.");
		descriptions.put("Travel",
				"Journey to fascinating destinations and discover travel tips from around the globe.");
		descriptions.put("Food", "Savor delicious recipes, cooking techniques, and culinary adventures.");
		descriptions.put("Health",
				"Learn about wellness practices, fitness advice, and maintaining a balanced lifestyle.");
		descriptions.put("Fashion", "Stay updated on style trends, design innovations, and sustainable fashion.");
		descriptions.put("Business", "Insights into entrepreneurship, management strategies, and market trends.");
		descriptions.put("Entertainment", "The latest in movies, music, art, and pop culture from around the world.");
		descriptions.put("Sports", "Coverage of sporting events, athlete profiles, and fitness techniques.");
		descriptions.put("Education", "Resources for learning, teaching methodologies, and educational trends.");
		descriptions.put("Nature", "Explore the beauty and wonders of our natural world through inspiring articles.");

		return descriptions.getOrDefault(category, "Discover interesting articles in this category.");
	}

	/**
	 * Get category icon class (Font Awesome)
	 * 
	 * @param category The category
	 * @return Font Awesome icon class for the category
	 */
	public String getCategoryIcon(String category) {
		Map<String, String> icons = new HashMap<>();
		icons.put("Technology", "fa-microchip");
		icons.put("Travel", "fa-plane");
		icons.put("Food", "fa-utensils");
		icons.put("Health", "fa-heartbeat");
		icons.put("Fashion", "fa-tshirt");
		icons.put("Business", "fa-briefcase");
		icons.put("Entertainment", "fa-film");
		icons.put("Sports", "fa-basketball-ball");
		icons.put("Education", "fa-graduation-cap");
		icons.put("Nature", "fa-leaf");
		icons.put("Other", "fa-folder");

		return icons.getOrDefault(category, "fa-newspaper");
	}
}