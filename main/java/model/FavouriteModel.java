package model;

public class FavouriteModel {
    private int favouriteId;
    private int blogId;
    private int userId;
    
    // Default constructor
    public FavouriteModel() {
    }
    
    // Parameterized constructor
    public FavouriteModel(int favouriteId, int blogId, int userId) {
        this.favouriteId = favouriteId;
        this.blogId = blogId;
        this.userId = userId;
    }
    
    // Getters and setters
    public int getFavouriteId() {
        return favouriteId;
    }
    
    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
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