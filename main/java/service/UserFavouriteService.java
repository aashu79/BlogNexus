package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import config.DbConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BlogModel;
import model.UserModel;
import utils.ImageUtil;
import utils.RedirectionUtil;

/**
 * Service class for handling user's favourite blogs
 * @author Aashu Kumar Sah
 */
public class UserFavouriteService {
	private Connection dbConnection;
	private ImageUtil imageUtil;
	private RedirectionUtil redirectionUtil;

	/**
	 * Constructor initializes database connection and utilities
	 */
	public UserFavouriteService() {
		this.imageUtil = new ImageUtil();
		this.redirectionUtil = new RedirectionUtil();

		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (Exception e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Get all favourite blogs for a user
	 * 
	 * @param userId ID of user to get favourites for
	 * @return List of BlogModel objects that are favourited by the user
	 */
	public List<BlogModel> getFavouriteBlogsByUserId(int userId) {
		List<BlogModel> favouriteBlogs = new ArrayList<>();

		String query = "SELECT b.*, u.first_name, u.last_name, u.profile_picture FROM blog b "
				+ "JOIN user u ON b.created_by = u.user_id " + "JOIN favourite f ON b.blog_id = f.blog_id "
				+ "WHERE f.user_id = ? " + "ORDER BY f.favourite_id DESC"; // Show newest favourites first

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					BlogModel blog = new BlogModel();
					blog.setBlogId(rs.getInt("blog_id"));
					blog.setTitle(rs.getString("title"));
					blog.setContent(rs.getString("content"));

					String thumbnailName = rs.getString("thumbnail");
					blog.setThumbnail(thumbnailName);
					blog.setThumbnailUrl(imageUtil.getThumbnailImageUrl(thumbnailName));

					String imageName = rs.getString("image");
					blog.setImage(imageName);
					blog.setImageUrl(imageUtil.getBlogImageUrl(imageName));

					blog.setGenre(rs.getString("genre"));
					blog.setCreatedBy(rs.getInt("created_by"));

					// Author info
					String authorFirstName = rs.getString("first_name");
					String authorLastName = rs.getString("last_name");
					blog.setAuthorName(authorFirstName + " " + authorLastName);

					// Handle created_at properly
					String createdAtStr = rs.getString("created_at");
					if (createdAtStr != null && !createdAtStr.isEmpty()) {
						try {
							if (createdAtStr.contains("T")) {
								blog.setCreatedAt(LocalDateTime.parse(createdAtStr));
							} else {
								// Use formatter if not in ISO format
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
								blog.setCreatedAt(LocalDateTime.parse(createdAtStr, formatter));
							}
						} catch (Exception e) {
							// If date parsing fails, set to current time
							blog.setCreatedAt(LocalDateTime.now());
							System.err.println("Error parsing date: " + e.getMessage());
						}
					} else {
						blog.setCreatedAt(LocalDateTime.now());
					}

					favouriteBlogs.add(blog);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving favourite blogs: " + e.getMessage());
			e.printStackTrace();
		}

		return favouriteBlogs;
	}

	/**
	 * Calculate read time for a blog
	 * 
	 * @param content Blog content
	 * @return Estimated read time in minutes
	 */
	public int calculateReadTime(String content) {
		if (content == null || content.isEmpty()) {
			return 1;
		}

		// Rough estimate: average 5 characters per word, 200 words per minute
		int characterCount = content.length();
		int wordCount = characterCount / 5;
		int readTimeMinutes = wordCount / 200;

		// Minimum read time is 1 minute
		return Math.max(1, readTimeMinutes);
	}

	/**
	 * Generate an excerpt from blog content
	 * 
	 * @param content   Full blog content
	 * @param maxLength Maximum length of excerpt
	 * @return Shortened excerpt with ellipsis if necessary
	 */
	public String generateExcerpt(String content, int maxLength) {
		if (content == null || content.isEmpty()) {
			return "";
		}

		if (content.length() <= maxLength) {
			return content;
		}

		// Try to find a space near the maxLength to avoid cutting words
		int cutoff = content.lastIndexOf(' ', maxLength);
		if (cutoff == -1) {
			cutoff = maxLength;
		}

		return content.substring(0, cutoff) + "...";
	}

	/**
	 * Remove a blog from user's favourites
	 * 
	 * @param blogId ID of blog to remove
	 * @param userId ID of user
	 * @return true if successful, false otherwise
	 */
	public boolean removeFromFavourites(int blogId, int userId) {
		String query = "DELETE FROM favourite WHERE blog_id = ? AND user_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setInt(2, userId);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error removing from favourites: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Process request to remove a blog from favourites
	 * 
	 * @param req HTTP request with blog ID
	 * @param res HTTP response for redirection
	 */
	public void processRemoveFavourite(HttpServletRequest req, HttpServletResponse res) {
		try {
			// Check if user is logged in
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/login", "error",
						"Please login to manage favourites");
				return;
			}

			UserModel user = (UserModel) session.getAttribute("user");
			String blogIdParam = req.getParameter("blogId");

			if (blogIdParam == null || blogIdParam.trim().isEmpty()) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/user/favourite", "error", "Invalid blog ID");
				return;
			}

			try {
				int blogId = Integer.parseInt(blogIdParam);
				boolean success = removeFromFavourites(blogId, user.getUserId());

				if (success) {
					redirectionUtil.urlRedirectWithMessage(req, res, "/user/favourite", "success",
							"Blog removed from favourites");
				} else {
					redirectionUtil.urlRedirectWithMessage(req, res, "/user/favourite", "error",
							"Failed to remove blog from favourites");
				}
			} catch (NumberFormatException e) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/user/favourite", "error", "Invalid blog ID format");
			}
		} catch (Exception e) {
			System.err.println("Error processing remove favourite: " + e.getMessage());
			e.printStackTrace();
			try {
				redirectionUtil.urlRedirectWithMessage(req, res, "/user/favourite", "error", "An error occurred");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Get author role based on blog genre
	 * 
	 * @param genre Blog genre
	 * @return Appropriate author role title
	 */
	public String getAuthorRoleByGenre(String genre) {
		if (genre == null || genre.isEmpty()) {
			return "Writer";
		}

		switch (genre.toLowerCase()) {
		case "technology":
			return "Tech Writer";
		case "health":
			return "Health Expert";
		case "food":
			return "Food Enthusiast";
		case "travel":
			return "Travel Blogger";
		case "nature":
			return "Environmental Writer";
		case "business":
			return "Business Analyst";
		case "sports":
			return "Sports Analyst";
		case "fashion":
			return "Fashion Blogger";
		case "education":
			return "Educator";
		case "wellness":
			return "Wellness Coach";
		case "environment":
			return "Environmental Advocate";
		default:
			return "Writer";
		}
	}
}