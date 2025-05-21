package model;

import java.time.LocalDateTime;

public class CommentModel {
	private int commentId;
	private int blogId;
	private String commentContent;
	private int commentedBy;
	private LocalDateTime commentedAt;

	// Additional fields for display
	private String commenterName;
	private String commenterProfilePicture;
	private String commenterProfilePictureUrl;

	// Default constructor
	public CommentModel() {
	}

	// Parameterized constructor
	public CommentModel(int commentId, int blogId, String commentContent, int commentedBy, LocalDateTime commentedAt) {
		this.commentId = commentId;
		this.blogId = blogId;
		this.commentContent = commentContent;
		this.commentedBy = commentedBy;
		this.commentedAt = commentedAt;
	}

	// Getters and setters
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(int commentedBy) {
		this.commentedBy = commentedBy;
	}

	public LocalDateTime getCommentedAt() {
		return commentedAt;
	}

	public void setCommentedAt(LocalDateTime commentedAt) {
		this.commentedAt = commentedAt;
	}

	public String getCommenterName() {
		return commenterName;
	}

	public void setCommenterName(String commenterName) {
		this.commenterName = commenterName;
	}

	public String getCommenterProfilePicture() {
		return commenterProfilePicture;
	}

	public void setCommenterProfilePicture(String commenterProfilePicture) {
		this.commenterProfilePicture = commenterProfilePicture;
	}

	public String getCommenterProfilePictureUrl() {
		return commenterProfilePictureUrl;
	}

	public void setCommenterProfilePictureUrl(String commenterProfilePictureUrl) {
		this.commenterProfilePictureUrl = commenterProfilePictureUrl;
	}
}