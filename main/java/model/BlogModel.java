package model;

import java.time.LocalDateTime;

/**
 * Model class representing a blog entry.
 * Contains fields for blog details such as title, content, images, genre, author, and timestamps.
 * 
 * @author Aashu Kumar Sah
 
 */
public class BlogModel {

    // Unique identifier for the blog
    private int blogId;
    // Title of the blog
    private String title;
    // Content/body of the blog
    private String content;
    // Thumbnail image file name or path
    private String thumbnail;
    // Main image file name or path
    private String image;
    // Genre/category of the blog
    private String genre;
    // User ID of the creator/author
    private int createdBy; // Assuming this is a user ID
    // Timestamp of when the blog was created
    private LocalDateTime createdAt;
    // Name of the author (for display purposes)
    private String authorName;

    // Default constructor
    public BlogModel() {
    }

    /**
     * Parameterized constructor (without blogId, for new blog creation)
     * 
     * @param title       Title of the blog
     * @param content     Content/body of the blog
     * @param thumbnail   Thumbnail image file/path
     * @param image       Main image file/path
     * @param genre       Genre/category
     * @param createdBy   User ID of creator
     * @param createdAt   Creation timestamp
     */
    public BlogModel(String title, String content, String thumbnail, String image, String genre, int createdBy,
            LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.image = image;
        this.genre = genre;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    /**
     * Parameterized constructor (with blogId, for existing blogs)
     * 
     * @param blogId      Unique identifier for the blog
     * @param title       Title of the blog
     * @param content     Content/body of the blog
     * @param thumbnail   Thumbnail image file/path
     * @param image       Main image file/path
     * @param genre       Genre/category
     * @param createdBy   User ID of creator
     * @param createdAt   Creation timestamp
     */
    public BlogModel(int blogId, String title, String content, String thumbnail, String image, String genre,
            int createdBy, LocalDateTime createdAt) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.image = image;
        this.genre = genre;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    /**
     * Gets the blog ID.
     * @return blogId
     */
    public int getBlogId() {
        return blogId;
    }

    /**
     * Sets the blog ID.
     * @param blogId Unique identifier for the blog
     */
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    /**
     * Gets the blog title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the blog title.
     * @param title Title of the blog
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the blog content.
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the blog content.
     * @param content Content of the blog
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the thumbnail path or name.
     * @return thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Sets the thumbnail path or name.
     * @param thumbnail Thumbnail file/path
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Gets the image path or name.
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image path or name.
     * @param image Main image file/path
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets the genre/category.
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre/category.
     * @param genre Genre or category
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the user ID of the creator.
     * @return createdBy
     */
    public int getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user ID of the creator.
     * @param createdBy User ID
     */
    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Gets the creation timestamp.
     * @return createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     * @param createdAt Timestamp of creation
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // URL to the thumbnail image (for external usage)
    private String thumbnailUrl;
    // URL to the main image (for external usage)
    private String imageUrl;

    /**
     * Gets the thumbnail URL.
     * @return thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Sets the thumbnail URL.
     * @param thumbnailUrl URL for the thumbnail image
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * Gets the image URL.
     * @return imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL.
     * @param imageUrl URL for the main image
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the author's name.
     * @return authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the author's name.
     * @param authorName Name of the author
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Returns a string representation of the BlogModel for debugging/logging.
     * @return String representation of BlogModel
     */
    @Override
    public String toString() {
        return "BlogModel{" + "blogId=" + blogId + ", title='" + title + '\'' + ", content='" + content + '\''
                + ", thumbnail='" + thumbnail + '\'' + ", image='" + image + '\'' + ", genre='" + genre + '\''
                + ", createdBy=" + createdBy + ", createdAt=" + createdAt + '}';
    }
}