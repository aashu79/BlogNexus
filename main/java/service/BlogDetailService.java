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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.BlogModel;
import model.CommentModel;
import model.UserModel;
import utils.ImageUtil;
import utils.RedirectionUtil;

/**
 * Service class providing functionality for blog detail pages including
 * comments, favourites, and likes
 */
public class BlogDetailService {
	private Connection dbConnection;
	private ImageUtil imageUtil;
	private RedirectionUtil redirectionUtil;

	/**
	 * Constructor initializes database connection and utilities
	 */
	public BlogDetailService() {
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
	 * Get blog details by ID including author info
	 * 
	 * @param blogId ID of the blog to retrieve
	 * @return BlogModel with author information or null if not found
	 */
	public BlogModel getBlogWithAuthorById(int blogId) {
		BlogModel blog = null;

		String query = "SELECT b.*, u.first_name, u.last_name, u.profile_picture FROM blog b "
				+ "JOIN user u ON b.created_by = u.user_id " + "WHERE b.blog_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					blog = new BlogModel();
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
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving blog: " + e.getMessage());
			e.printStackTrace();
		}

		return blog;
	}

	/**
	 * Get author profile picture URL by blog ID
	 * 
	 * @param blogId ID of the blog to get author profile picture for
	 * @return URL string for the profile picture or null if not found
	 */
	public String getAuthorProfilePictureUrl(int blogId) {
		String profilePictureUrl = null;

		String query = "SELECT u.profile_picture FROM blog b " + "JOIN user u ON b.created_by = u.user_id "
				+ "WHERE b.blog_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String profilePicture = rs.getString("profile_picture");
					if (profilePicture != null && !profilePicture.isEmpty()) {
						profilePictureUrl = imageUtil.getProfileImageUrl(profilePicture);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving author profile picture: " + e.getMessage());
		}

		return profilePictureUrl;
	}

	/**
	 * Determine author role based on blog genre
	 * 
	 * @param genre Genre of the blog
	 * @return String representation of author role based on genre
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
		default:
			return "Writer";
		}
	}

	/**
	 * Get all comments for a blog
	 * 
	 * @param blogId ID of the blog to retrieve comments for
	 * @return List of CommentModel objects for the specified blog
	 */
	public List<CommentModel> getCommentsByBlogId(int blogId) {
		List<CommentModel> comments = new ArrayList<>();

		String query = "SELECT c.*, u.first_name, u.last_name, u.profile_picture " + "FROM comment c "
				+ "JOIN user u ON c.commented_by = u.user_id " + "WHERE c.blog_id = ? "
				+ "ORDER BY c.commented_at DESC";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					CommentModel comment = new CommentModel();
					comment.setCommentId(rs.getInt("comment_id"));
					comment.setBlogId(rs.getInt("blog_id"));
					comment.setCommentContent(rs.getString("comment_content"));
					comment.setCommentedBy(rs.getInt("commented_by"));

					// Parse commented_at from string to LocalDateTime
					String commentedAtStr = rs.getString("commented_at");
					if (commentedAtStr != null && !commentedAtStr.isEmpty()) {
						try {
							if (commentedAtStr.contains("T")) {
								comment.setCommentedAt(LocalDateTime.parse(commentedAtStr));
							} else {
								// Use formatter if not in ISO format
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
								comment.setCommentedAt(LocalDateTime.parse(commentedAtStr, formatter));
							}
						} catch (Exception e) {
							comment.setCommentedAt(LocalDateTime.now());
						}
					} else {
						comment.setCommentedAt(LocalDateTime.now());
					}

					// Commenter details
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					comment.setCommenterName(firstName + " " + lastName);

					String profilePicture = rs.getString("profile_picture");
					comment.setCommenterProfilePicture(profilePicture);

					if (profilePicture != null && !profilePicture.isEmpty()) {
						comment.setCommenterProfilePictureUrl(imageUtil.getProfileImageUrl(profilePicture));
					}

					comments.add(comment);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving comments: " + e.getMessage());
			e.printStackTrace();
		}

		return comments;
	}

	/**
	 * Add a new comment
	 * 
	 * @param blogId  ID of the blog to comment on
	 * @param userId  ID of the user making the comment
	 * @param content Content of the comment
	 * @return true if comment added successfully, false otherwise
	 */
	public boolean addComment(int blogId, int userId, String content) {
		String query = "INSERT INTO comment (blog_id, comment_content, commented_by, commented_at) VALUES (?, ?, ?, ?)";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setString(2, content);
			stmt.setInt(3, userId);
			stmt.setString(4, LocalDateTime.now().toString());

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error adding comment: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Check if user has favourited a blog
	 * 
	 * @param blogId ID of the blog to check
	 * @param userId ID of the user
	 * @return true if blog is favourited by user, false otherwise
	 */
	public boolean isFavourite(int blogId, int userId) {
		String query = "SELECT COUNT(*) as count FROM favourite WHERE blog_id = ? AND user_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setInt(2, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("count") > 0;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error checking favourite status: " + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Add blog to favourites
	 * 
	 * @param blogId ID of the blog to favourite
	 * @param userId ID of the user
	 * @return true if successful, false otherwise
	 */
	public boolean addToFavourites(int blogId, int userId) {
		// First check if already favourited
		if (isFavourite(blogId, userId)) {
			return true;
		}

		String query = "INSERT INTO favourite (blog_id, user_id) VALUES (?, ?)";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setInt(2, userId);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error adding to favourites: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove blog from favourites
	 * 
	 * @param blogId ID of the blog to unfavourite
	 * @param userId ID of the user
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
	 * Check if user has liked a blog
	 * 
	 * @param blogId ID of the blog to check
	 * @param userId ID of the user
	 * @return true if blog is liked by user, false otherwise
	 */
	public boolean isLiked(int blogId, int userId) {
		String query = "SELECT COUNT(*) as count FROM likes WHERE blog_id = ? AND user_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setInt(2, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("count") > 0;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error checking like status: " + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Add like to a blog
	 * 
	 * @param blogId ID of the blog to like
	 * @param userId ID of the user
	 * @return true if successful, false otherwise
	 */
	public boolean addLike(int blogId, int userId) {
		// First check if already liked
		if (isLiked(blogId, userId)) {
			return true;
		}

		String query = "INSERT INTO likes (blog_id, user_id) VALUES (?, ?)";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setInt(2, userId);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error adding like: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove like from a blog
	 * 
	 * @param blogId ID of the blog to unlike
	 * @param userId ID of the user
	 * @return true if successful, false otherwise
	 */
	public boolean removeLike(int blogId, int userId) {
		String query = "DELETE FROM likes WHERE blog_id = ? AND user_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.setInt(2, userId);

			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error removing like: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Get count of likes for a blog
	 * 
	 * @param blogId ID of the blog to count likes for
	 * @return Number of likes
	 */
	public int getLikeCount(int blogId) {
		String query = "SELECT COUNT(*) as count FROM likes WHERE blog_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error getting like count: " + e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Get count of comments for a blog
	 * 
	 * @param blogId ID of the blog to count comments for
	 * @return Number of comments
	 */
	public int getCommentCount(int blogId) {
		String query = "SELECT COUNT(*) as count FROM comment WHERE blog_id = ?";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error getting comment count: " + e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Calculate read time based on content length (approx 200 words per minute)
	 * 
	 * @param content Blog content to calculate read time for
	 * @return Estimated reading time in minutes
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
	 * Process comment form submission
	 * 
	 * @param req HTTP request with comment data
	 * @param res HTTP response for redirection
	 * @throws IOException If redirection fails
	 */
	public void processCommentSubmission(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/login", "error", "Please login to comment");
				return;
			}

			UserModel user = (UserModel) session.getAttribute("user");
			String blogIdParam = req.getParameter("blogId");
			String commentContent = req.getParameter("commentContent");

			if (blogIdParam == null || commentContent == null || commentContent.trim().isEmpty()) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blog/" + blogIdParam, "error",
						"Comment cannot be empty");
				return;
			}

			int blogId = Integer.parseInt(blogIdParam);
			boolean success = addComment(blogId, user.getUserId(), commentContent);

			if (success) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blog/" + blogId, "success",
						"Comment added successfully");
			} else {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blog/" + blogId, "error", "Failed to add comment");
			}

		} catch (Exception e) {
			System.err.println("Error processing comment: " + e.getMessage());
			e.printStackTrace();
			try {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blogs", "error", "An error occurred");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Process favourite toggle
	 * 
	 * @param req HTTP request with favourite action data
	 * @param res HTTP response for redirection
	 * @throws IOException If redirection fails
	 */
	public void processFavouriteToggle(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/login", "error", "Please login to favourite blogs");
				return;
			}

			UserModel user = (UserModel) session.getAttribute("user");
			String blogIdParam = req.getParameter("blogId");
			String action = req.getParameter("action");

			if (blogIdParam == null || action == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blogs", "error", "Invalid request parameters");
				return;
			}

			int blogId = Integer.parseInt(blogIdParam);
			boolean success = false;

			if ("add".equals(action)) {
				success = addToFavourites(blogId, user.getUserId());
			} else if ("remove".equals(action)) {
				success = removeFromFavourites(blogId, user.getUserId());
			}

			if (success) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blog/" + blogId, "success",
						"add".equals(action) ? "Added to favourites" : "Removed from favourites");
			} else {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blog/" + blogId, "error",
						"Failed to update favourites");
			}

		} catch (Exception e) {
			System.err.println("Error processing favourite: " + e.getMessage());
			e.printStackTrace();
			try {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blogs", "error", "An error occurred");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Process like toggle
	 * 
	 * @param req HTTP request with like action data
	 * @param res HTTP response for redirection
	 * @throws IOException If redirection fails
	 */
	public void processLikeToggle(HttpServletRequest req, HttpServletResponse res) {
		try {
			HttpSession session = req.getSession(false);
			if (session == null || session.getAttribute("user") == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/login", "error", "Please login to like blogs");
				return;
			}

			UserModel user = (UserModel) session.getAttribute("user");
			String blogIdParam = req.getParameter("blogId");
			String action = req.getParameter("action");

			if (blogIdParam == null || action == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blogs", "error", "Invalid request parameters");
				return;
			}

			int blogId = Integer.parseInt(blogIdParam);
			boolean success = false;

			if ("add".equals(action)) {
				success = addLike(blogId, user.getUserId());
			} else if ("remove".equals(action)) {
				success = removeLike(blogId, user.getUserId());
			}

			if (success) {
				redirectionUtil.urlRedirect(req, res, "/blog/" + blogId);
			} else {
				redirectionUtil.urlRedirect(req, res, "/blog/" + blogId);
			}

		} catch (Exception e) {
			System.err.println("Error processing like: " + e.getMessage());
			e.printStackTrace();
			try {
				redirectionUtil.urlRedirectWithMessage(req, res, "/blogs", "error", "An error occurred");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}