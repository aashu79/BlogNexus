package model;

public class LikeModel {
    private int likedId;
    private int blogId;
    private int userId;
    
    // Default constructor
    public LikeModel() {
    }
    
    // Parameterized constructor
    public LikeModel(int likedId, int blogId, int userId) {
        this.likedId = likedId;
        this.blogId = blogId;
        this.userId = userId;
    }
    
    // Getters and setters
    public int getLikedId() {
        return likedId;
    }
    
    public void setLikedId(int likedId) {
        this.likedId = likedId;
    }
    
    public int getBlogId() {
        return blogId;
    }
    
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
}