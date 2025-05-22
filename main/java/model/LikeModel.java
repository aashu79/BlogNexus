package model;

/**
 * Model class representing a like on a blog post.
 * Stores information about which user liked which blog.
 *
 * @author Aashu Kumar Sah
 */
public class LikeModel {
    // Unique identifier for the like entry
    private int likedId;
    // Identifier for the blog that is liked
    private int blogId;
    // Identifier for the user who liked the blog
    private int userId;

    /**
     * Default constructor
     */
    public LikeModel() {
    }

    /**
     * Parameterized constructor
     *
     * @param likedId Unique identifier for the like entry
     * @param blogId  Identifier for the blog
     * @param userId  Identifier for the user
     */
    public LikeModel(int likedId, int blogId, int userId) {
        this.likedId = likedId;
        this.blogId = blogId;
        this.userId = userId;
    }

    /**
     * Gets the like ID.
     * @return likedId
     */
    public int getLikedId() {
        return likedId;
    }

    /**
     * Sets the like ID.
     * @param likedId Unique identifier for the like entry
     */
    public void setLikedId(int likedId) {
        this.likedId = likedId;
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