package service;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import config.DbConfig;
import model.BlogModel;
import model.UserModel;
import utils.ImageUtil;
import utils.RedirectionUtil;
import utils.ValidationResult;
import java.time.format.DateTimeFormatter;

/**
 * Service class for handling blog operations.
 */
public class BlogService {

	private Connection dbConnection;
	private RedirectionUtil redirectionUtil;
	private ImageUtil imageUtil;

	/**
	 * Initializes blog service with required dependencies.
	 */
	public BlogService() {
		this.redirectionUtil = new RedirectionUtil();
		this.imageUtil = new ImageUtil();

		try {
			this.dbConnection = DbConfig.getDbConnection();
		} catch (Exception e) {
			System.err.println("Database connection error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new blog using authenticated user's session.
	 * 
	 * @param req HTTP request with blog data
	 * @param res HTTP response for redirection
	 * @throws IOException      If redirect operation fails
	 * @throws ServletException If request processing fails
	 */
	public void createBlog(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (dbConnection == null) {
			redirectionUtil.redirectWithMessage(req, res, "blog_creation.jsp", "error",
					"Database connection unavailable");
			return;
		}

		// Get user from session - at this point, filter already ensured user is logged
		// in
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		int userId = user.getUserId();

		// Get blog parameters
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String genre = req.getParameter("genre");
		Part thumbnail = req.getPart("thumbnail");
		Part image = req.getPart("image");

		// Validate inputs
		ValidationResult validation = validateBlogInputs(title, content, genre, thumbnail, image);
		if (!validation.isSuccess()) {
			redirectionUtil.redirectWithMessage(req, res, "blog_creation.jsp", "error", validation.getMessage());
			return;
		}

		// Upload images with the specialized methods
		String thumbnailName = imageUtil.uploadThumbnailImage(thumbnail);
		String imageName = imageUtil.uploadBlogImage(image);

		if (thumbnailName == null || imageName == null) {
			redirectionUtil.redirectWithMessage(req, res, "blog_creation.jsp", "error", "Failed to upload images");
			return;
		}

		// Insert blog into database
		String query = "INSERT INTO blog (title, content, thumbnail, image, genre, created_by, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setString(3, thumbnailName);
			stmt.setString(4, imageName);
			stmt.setString(5, genre);
			stmt.setInt(6, userId);
			stmt.setString(7, LocalDateTime.now().toString());

			int result = stmt.executeUpdate();

			if (result > 0) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", "success",
						"Blog published successfully!");
			} else {
				redirectionUtil.redirectWithMessage(req, res, "blog_creation.jsp", "error", "Failed to publish blog");
			}
		} catch (SQLException e) {
			System.err.println("Error creating blog: " + e.getMessage());
			redirectionUtil.redirectWithMessage(req, res, "blog_creation.jsp", "error", "Database error occurred");
		}
	}

	/**
	 * Validates blog input fields.
	 * 
	 * @param title     Blog title
	 * @param content   Blog content
	 * @param genre     Blog genre
	 * @param thumbnail Blog thumbnail image
	 * @param image     Blog main image
	 * @return ValidationResult with status and message
	 */
	private ValidationResult validateBlogInputs(String title, String content, String genre, Part thumbnail,
			Part image) {
		if (title == null || title.trim().isEmpty()) {
			return new ValidationResult(false, "Title is required");
		}

		if (content == null || content.trim().isEmpty()) {
			return new ValidationResult(false, "Content is required");
		}

		if (genre == null || genre.trim().isEmpty()) {
			return new ValidationResult(false, "Genre is required");
		}

		if (thumbnail == null || thumbnail.getSize() <= 0) {
			return new ValidationResult(false, "Thumbnail is required");
		}

		if (image == null || image.getSize() <= 0) {
			return new ValidationResult(false, "Image is required");
		}

		return new ValidationResult(true, "");
	}

	/**
	 * Retrieves all blogs or filtered by genre.
	 * 
	 * @param req HTTP request with optional genre filter
	 * @param res HTTP response
	 * @throws ServletException If request processing fails
	 * @throws IOException      If IO operation fails
	 */
	public void getAllBlogs(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String genre = req.getParameter("genre");
		List<BlogModel> blogs = new ArrayList<>();

		String query = "SELECT b.*, u.first_name, u.last_name FROM blog b JOIN user u ON b.created_by = u.user_id";
		if (genre != null && !genre.trim().isEmpty()) {
			query += " WHERE b.genre = ?";
		}
		query += " ORDER BY b.created_at DESC";

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			if (genre != null && !genre.trim().isEmpty()) {
				stmt.setString(1, genre);
			}

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
					blog.setCreatedAt(LocalDateTime.parse(rs.getString("created_at")));

					// Add author name as request attribute
					blogs.add(blog);
				}
			}

			req.setAttribute("blogs", blogs);
			req.getRequestDispatcher("blogs.jsp").forward(req, res);
		} catch (SQLException e) {
			System.err.println("Error retrieving blogs: " + e.getMessage());
			redirectionUtil.redirectWithMessage(req, res, "error.jsp", "error", "Failed to retrieve blogs");
		}
	}

	/**
	 * Gets blogs created by a specific user.
	 * 
	 * @param userId The ID of the user
	 * @return List of blogs created by the user
	 */
	public List<BlogModel> getBlogsByUserId(int userId) {
		List<BlogModel> blogs = new ArrayList<>();

		String query = "SELECT b.* FROM blog b " + "WHERE b.created_by = ? " + "ORDER BY b.created_at DESC";

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

					// Skip setting the createdAt field since it's causing issues

					blogs.add(blog);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error getting blogs for user " + userId + ": " + e.getMessage());
			e.printStackTrace();
		}

		return blogs;
	}

	/**
	 * Retrieves a blog by its ID.
	 * 
	 * @param blogId ID of the blog to retrieve
	 * @return BlogModel if found, null otherwise
	 */
	public BlogModel getBlogById(int blogId) {
		BlogModel blog = null;

		String query = "SELECT b.* FROM blog b WHERE b.blog_id = ?";

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

					// Skip setting the createdAt field since it's causing issues in other methods
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving blog with ID " + blogId + ": " + e.getMessage());
			e.printStackTrace();
		}

		return blog;
	}

	/**
	 * Updates an existing blog.
	 * 
	 * @param req HTTP request with updated blog data
	 * @param res HTTP response for redirection
	 * @throws IOException      If redirect operation fails
	 * @throws ServletException If request processing fails
	 */
	public void updateBlog(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// Get user from session
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		// Get blog ID from request
		String blogIdStr = req.getParameter("id");
		if (blogIdStr == null || blogIdStr.isEmpty()) {
			redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", "error", "Blog ID is required");
			return;
		}

		try {
			int blogId = Integer.parseInt(blogIdStr);
			int userId = user.getUserId();

			// Get form data
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String genre = req.getParameter("genre");

			// Validate required fields
			if (title == null || content == null || genre == null) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", "error", "Required fields missing");
				return;
			}

			// Check if user is authorized
			String checkQuery = "SELECT created_by FROM blog WHERE blog_id = ?";
			try (PreparedStatement stmt = dbConnection.prepareStatement(checkQuery)) {
				stmt.setInt(1, blogId);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						int creatorId = rs.getInt("created_by");
						boolean isAdmin = "admin".equals(user.getUserRole());
						if (creatorId != userId && !isAdmin) {
							redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", "error",
									"Not authorized");
							return;
						}
					} else {
						redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", "error", "Blog not found");
						return;
					}
				}
			}

			// Build the update query
			StringBuilder query = new StringBuilder("UPDATE blog SET title = ?, content = ?, genre = ?");
			ArrayList<Object> params = new ArrayList<>();
			params.add(title);
			params.add(content);
			params.add(genre);

			// Handle thumbnail image upload if provided
			Part thumbnail = req.getPart("thumbnail");
			if (thumbnail != null && thumbnail.getSize() > 0) {
				String thumbnailName = imageUtil.uploadThumbnailImage(thumbnail);

				if (thumbnailName != null) {
					query.append(", thumbnail = ?");
					params.add(thumbnailName);
				} else {
					redirectionUtil.urlRedirectWithMessage(req, res, "/user/blog/edit/" + blogId, "error",
							"Failed to upload thumbnail image");
					return;
				}
			}

			// Handle main image upload if provided
			Part image = req.getPart("image");
			if (image != null && image.getSize() > 0) {
				String imageName = imageUtil.uploadBlogImage(image);

				if (imageName != null) {
					query.append(", image = ?");
					params.add(imageName);
				} else {
					redirectionUtil.urlRedirectWithMessage(req, res, "/user/blog/edit/" + blogId, "error",
							"Failed to upload main image");
					return;
				}
			}

			// Complete and execute the query
			query.append(" WHERE blog_id = ?");
			params.add(blogId);

			try (PreparedStatement stmt = dbConnection.prepareStatement(query.toString())) {
				for (int i = 0; i < params.size(); i++) {
					stmt.setObject(i + 1, params.get(i));
				}

				int result = stmt.executeUpdate();
				String message = result > 0 ? "success" : "error";
				String text = result > 0 ? "Blog updated successfully" : "Failed to update blog";

				redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", message, text);
			}

		} catch (Exception e) {
			redirectionUtil.urlRedirectWithMessage(req, res, "/user/profile", "error", "Error: " + e.getMessage());
		}
	}

	/**
	 * Deletes a blog by ID, including all related records in other tables.
	 * 
	 * @param req HTTP request with blog ID parameter
	 * @param res HTTP response for redirection
	 * @throws IOException If redirect operation fails
	 */
	public void deleteBlog(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// Get authenticated user info (authentication already handled by filter)
		HttpSession session = req.getSession(false);
		UserModel user = (UserModel) session.getAttribute("user");
		int userId = user.getUserId();
		String userRole = user.getUserRole();

		String blogIdParam = req.getParameter("id");
		if (blogIdParam == null || blogIdParam.trim().isEmpty()) {
			redirectionUtil.urlRedirectWithMessage(req, res, "/", "error", "Blog ID is required");
			return;
		}

		try {
			int blogId = Integer.parseInt(blogIdParam);

			// Authorization check - only owner or admin can delete
			if (!isUserAuthorizedForBlog(userId, userRole, blogId)) {
				redirectionUtil.urlRedirectWithMessage(req, res, "/", "error", "Not authorized to delete this blog");
				return;
			}

			// Start transaction
			dbConnection.setAutoCommit(false);

			try {
				// First delete records from related tables
				// Delete comments
				deleteRelatedRecords("comment", "blog_id", blogId);

				// Delete likes
				deleteRelatedRecords("likes", "blog_id", blogId);

				// Delete favorites
				deleteRelatedRecords("favourite", "blog_id", blogId);

				// Now delete the blog itself
				String query = "DELETE FROM blog WHERE blog_id = ?";
				try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
					stmt.setInt(1, blogId);
					int result = stmt.executeUpdate();

					if (result > 0) {
						// Commit transaction
						dbConnection.commit();

						String redirectUrl = "admin".equalsIgnoreCase(userRole) ? "/admin/blogs" : "/user/profile";
						redirectionUtil.urlRedirectWithMessage(req, res, redirectUrl, "success",
								"Blog deleted successfully");
					} else {
						// Rollback transaction
						dbConnection.rollback();

						String redirectUrl = "admin".equalsIgnoreCase(userRole) ? "/admin/blogs" : "/user/profile";
						redirectionUtil.urlRedirectWithMessage(req, res, redirectUrl, "error", "Failed to delete blog");
					}
				}
			} catch (Exception e) {
				// Rollback transaction on any error
				dbConnection.rollback();
				throw e;
			} finally {
				// Reset auto-commit
				dbConnection.setAutoCommit(true);
			}
		} catch (NumberFormatException e) {
			String redirectUrl = "admin".equalsIgnoreCase(userRole) ? "/admin/blogs" : "/user/profile";
			redirectionUtil.urlRedirectWithMessage(req, res, redirectUrl, "error", "Invalid blog ID format");
		} catch (SQLException e) {
			System.err.println("Error deleting blog: " + e.getMessage());
			String redirectUrl = "admin".equalsIgnoreCase(userRole) ? "/admin/blogs" : "/user/profile";
			redirectionUtil.urlRedirectWithMessage(req, res, redirectUrl, "error", "Database error");
		}
	}

	/**
	 * Helper method to delete related records from a specific table
	 * 
	 * @param tableName  Table to delete from
	 * @param columnName Column name for the foreign key
	 * @param blogId     Blog ID to match
	 * @throws SQLException If deletion fails
	 */
	private void deleteRelatedRecords(String tableName, String columnName, int blogId) throws SQLException {
		String query = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);
			stmt.executeUpdate();
		}
	}

	/**
	 * Checks if user is authorized to modify/delete a blog.
	 * 
	 * @param userId   Current user's ID
	 * @param userRole Current user's role
	 * @param blogId   ID of blog to check
	 * @return True if authorized, false otherwise
	 */
	private boolean isUserAuthorizedForBlog(int userId, String userRole, int blogId) {
		// Admins can update/delete any blog
		if ("admin".equalsIgnoreCase(userRole)) {
			return true;
		}

		// Check if user is the blog owner
		String query = "SELECT created_by FROM blog WHERE blog_id = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, blogId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					int creatorId = rs.getInt("created_by");
					return creatorId == userId;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error checking blog authorization: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Get all blogs as a list for admin display.
	 * 
	 * @param query SQL query to execute
	 * @return List of blog models
	 */
	public List<BlogModel> getAllBlogsForAdmin(String query) {
		List<BlogModel> blogs = new ArrayList<>();

		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
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

					// Handle created_at carefully to avoid format issues
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

					blogs.add(blog);
				}
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving blogs: " + e.getMessage());
			e.printStackTrace();
		}

		return blogs;
	}

	/**
	 * 
	 * @param userId User ID to look up
	 * @return Full name of the user
	 */
	public String getAuthorNameById(int userId) {
		String fullName = "Unknown Author";

		String query = "SELECT first_name, last_name FROM user WHERE user_id = ?";
		try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
			stmt.setInt(1, userId);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					fullName = firstName + " " + lastName;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error getting author name: " + e.getMessage());
		}

		return fullName;
	}
}