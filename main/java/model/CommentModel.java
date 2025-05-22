package model;

import java.time.LocalDateTime;

/**
 * Model class representing a comment on a blog post.
 *
 * @author Aashu Kumar Sah
 */
public class CommentModel {
    // Unique identifier for the comment
    private int commentId;
    // Identifier for the blog post this comment belongs to
    private int blogId;
    // Content of the comment
    private String commentContent;
    // User ID of the person who commented
    private int commentedBy;
    // Date and time when the comment was made
    private LocalDateTime commentedAt;

    // Additional fields for display

    // Name of the commenter (for display purposes)
    private String commenterName;
    // File name or path of the commenter's profile picture
    private String commenterProfilePicture;
    // URL to the commenter's profile picture
    private String commenterProfilePictureUrl;

    /**
     * Default constructor
     */
    public CommentModel() {
    }

    /**
     * Parameterized constructor
     * 
     * @param commentId   Unique identifier for the comment
     * @param blogId      Blog post ID to which this comment belongs
     * @param commentContent Content of the comment
     * @param commentedBy User ID of the commenter
     * @param commentedAt Date and time when the comment was made
     */
    public CommentModel(int commentId, int blogId, String commentContent, int commentedBy, LocalDateTime commentedAt) {
        this.commentId = commentId;
        this.blogId = blogId;
        this.commentContent = commentContent;
        this.commentedBy = commentedBy;
        this.commentedAt = commentedAt;
    }

    // Getters and setters

    /**
     * Gets the comment ID.
     * @return commentId
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * Sets the comment ID.
     * @param commentId Unique identifier for the comment
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    /**
     * Gets the blog ID.
     * @return blogId
     */
    public int getBlogId() {
        return blogId;
    }

    /**
     * Sets the blog ID.
     * @param blogId Blog post ID
     */
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    /**
     * Gets the content of the comment.
     * @return commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * Sets the content of the comment.
     * @param commentContent Content of the comment
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * Gets the user ID of the commenter.
     * @return commentedBy
     */
    public int getCommentedBy() {
        return commentedBy;
    }

    /**
     * Sets the user ID of the commenter.
     * @param commentedBy User ID of the commenter
     */
    public void setCommentedBy(int commentedBy) {
        this.commentedBy = commentedBy;
    }

    /**
     * Gets the date and time when the comment was made.
     * @return commentedAt
     */
    public LocalDateTime getCommentedAt() {
        return commentedAt;
    }

    /**
     * Sets the date and time when the comment was made.
     * @param commentedAt Date and time of the comment
     */
    public void setCommentedAt(LocalDateTime commentedAt) {
        this.commentedAt = commentedAt;
    }

    /**
     * Gets the commenter's name.
     * @return commenterName
     */
    public String getCommenterName() {
        return commenterName;
    }

    /**
     * Sets the commenter's name.
     * @param commenterName Name of the commenter
     */
    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    /**
     * Gets the path or file name of the commenter's profile picture.
     * @return commenterProfilePicture
     */
    public String getCommenterProfilePicture() {
        return commenterProfilePicture;
    }

    /**
     * Sets the path or file name of the commenter's profile picture.
     * @param commenterProfilePicture File name or path to the profile picture
     */
    public void setCommenterProfilePicture(String commenterProfilePicture) {
        this.commenterProfilePicture = commenterProfilePicture;
    }

    /**
     * Gets the URL to the commenter's profile picture.
     * @return commenterProfilePictureUrl
     */
    public String getCommenterProfilePictureUrl() {
        return commenterProfilePictureUrl;
    }

    /**
     * Sets the URL to the commenter's profile picture.
     * @param commenterProfilePictureUrl URL to the profile picture
     */
    public void setCommenterProfilePictureUrl(String commenterProfilePictureUrl) {
        this.commenterProfilePictureUrl = commenterProfilePictureUrl;
    }
}