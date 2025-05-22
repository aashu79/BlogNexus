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
 * Service class for handling trending blogs operations.
 * @author Aashu Kumar Sah
 */
public class TrendingService {

	private Connection dbConnection;
	private ImageUtil imageUtil;

	/**
	 * Initializes trending service with required dependencies.
	 */
	public TrendingService() {
		this.imageUtil = new ImageUtil();

		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (Exception e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves a specified number of random blogs with author details.
	 * 
	 * @param count Number of blogs to retrieve
	 * @return Map containing blog list and author details
	 */
	public Map<String, Object> getRandomBlogsWithAuthors(int count) {
		List<BlogModel> blogs = new ArrayList<>();
		Map<Integer, Map<String, String>> authorDetails = new HashMap<>();

		// Query to get random blogs with author information
		String query = "SELECT b.*, u.first_name, u.last_name, u.profile_picture, u.user_role " + "FROM blog b "
				+ "JOIN user u ON b.created_by = u.user_id " + "ORDER BY RAND() LIMIT ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, count);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					BlogModel blog = new BlogModel();
					blog.setBlogId(rs.getInt("blog_id"));
					blog.setTitle(rs.getString("title"));

					// Get content excerpt (first 150 chars)
					String content = rs.getString("content");
					String excerpt = content.length() > 150 ? content.substring(0, 147) + "..." : content;
					blog.setContent(excerpt);

					// Get thumbnail with URL
					String thumbnailName = rs.getString("thumbnail");
					blog.setThumbnail(thumbnailName);
					blog.setThumbnailUrl(imageUtil.getThumbnailImageUrl(thumbnailName));

					// Get main image with URL
					String imageName = rs.getString("image");
					blog.setImage(imageName);
					blog.setImageUrl(imageUtil.getBlogImageUrl(imageName));

					blog.setGenre(rs.getString("genre"));
					blog.setCreatedBy(rs.getInt("created_by"));

					// Set the author name from database
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

					// Store additional author details
					Map<String, String> details = new HashMap<>();
					details.put("authorRole", rs.getString("user_role"));

					// Process profile picture
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

					// Add to collections
					blogs.add(blog);
					authorDetails.put(blog.getBlogId(), details);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving random blogs: " + e.getMessage());
			e.printStackTrace();
		}

		// Return both blog list and author details
		Map<String, Object> result = new HashMap<>();
		result.put("blogs", blogs);
		result.put("authorDetails", authorDetails);
		return result;
	}

	/**
	 * Format a date for display
	 * 
	 * @param dateTime The date to format
	 * @return Formatted date string
	 */
	public String formatDate(LocalDateTime dateTime) {
		if (dateTime == null) {
			return "Recent post";
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
		return dateTime.format(formatter);
	}

	/**
	 * Calculate estimated read time based on content length
	 * 
	 * @param content The blog content
	 * @return Read time string
	 */
	public String calculateReadTime(String content) {
		if (content == null || content.isEmpty()) {
			return "1 min read";
		}

		// Assume average reading speed of 200 words per minute
		int wordCount = content.split("\\s+").length;
		int readMinutes = Math.max(1, wordCount / 200);
		return readMinutes + " min read";
	}
}