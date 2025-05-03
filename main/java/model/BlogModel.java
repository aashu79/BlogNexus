package model;

import java.time.LocalDateTime;

public class BlogModel {

    private int blogId;
    private String title;
    private String content;
    private String thumbnail;
    private String image;
    private String genre;
    private int createdBy; // Assuming this is a user ID
    private LocalDateTime createdAt;
    private String authorName;


    // Default constructor
    public BlogModel() {
    }

    // Parameterized constructor (without blogId, for new blog creation)
    public BlogModel(String title, String content, String thumbnail, String image, 
                    String genre, int createdBy, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.image = image;
        this.genre = genre;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // Parameterized constructor (with blogId, for existing blogs)
    public BlogModel(int blogId, String title, String content, String thumbnail, String image, 
                    String genre, int createdBy, LocalDateTime createdAt) {
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
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    

    private String thumbnailUrl;
    private String imageUrl;

   
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
  

 public String getAuthorName() {
     return authorName;
 }

 public void setAuthorName(String authorName) {
     this.authorName = authorName;
 }

    // toString method for debugging/logging
    @Override
    public String toString() {
        return "BlogModel{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", image='" + image + '\'' +
                ", genre='" + genre + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                '}';
    }
}