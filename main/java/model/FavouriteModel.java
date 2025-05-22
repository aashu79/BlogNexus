package model;

/**
 * Model class representing a user's favourite blog entry.
 * Stores information about which user has favourited which blog.
 * 
 * @author Aashu Kumar Sah
 */
public class FavouriteModel {
    // Unique identifier for the favourite entry
    private int favouriteId;
    // Identifier for the blog that is favourited
    private int blogId;
    // Identifier for the user who favourited the blog
    private int userId;

    /**
     * Default constructor
     */
    public FavouriteModel() {
    }

    /**
     * Parameterized constructor
     *
     * @param favouriteId Unique identifier for the favourite entry
     * @param blogId      Identifier for the blog
     * @param userId      Identifier for the user
     */
    public FavouriteModel(int favouriteId, int blogId, int userId) {
        this.favouriteId = favouriteId;
        this.blogId = blogId;
        this.userId = userId;
    }

    /**
     * Gets the favourite ID.
     * @return favouriteId
     */
    public int getFavouriteId() {
        return favouriteId;
    }

    /**
     * Sets the favourite ID.
     * @param favouriteId Unique identifier for the favourite entry
     */
    public void setFavouriteId(int favouriteId) {
        this.favouriteId = favouriteId;
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
     * @param blogId Identifier for the blog
     */
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    /**
     * Gets the user ID.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId Identifier for the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}