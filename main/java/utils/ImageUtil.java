package utils;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.http.Part;

/**
 * Utility class for handling image operations including uploads and path resolution.
 */
public class ImageUtil {
    
    // Base directory configuration - this could also come from a properties file
    private static final String BASE_IMAGE_DIR = "F:/coursework-2nd semester 2nd year/java/code/src/main/webapp/resources/images";
    
    // Directory structure constants
    private static final String PROFILE_DIR = "profiles";
    private static final String BLOG_THUMBNAILS_DIR = "blogThumbnails";
    private static final String BLOG_IMAGES_DIR = "blogImages";
    
    /**
     * Extracts the file name from the given {@link Part} object.
     * 
     * @param part the {@link Part} object representing the uploaded file
     * @return the extracted file name or default if none found
     */
    public String getImageNameFromPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        String imageName = null;

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                imageName = s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }

        if (imageName == null || imageName.isEmpty()) {
            imageName = "default_" + System.currentTimeMillis() + ".png";
        }

        return imageName;
    }

    /**
     * Gets the full path to a profile image.
     * 
     * @param filename the name of the image file
     * @return the full path to the profile image
     */
    public String getProfileImagePath(String filename) {
        return BASE_IMAGE_DIR + File.separator + PROFILE_DIR + File.separator + filename;
    }
    
    /**
     * Gets the full path to a blog thumbnail image.
     * 
     * @param filename the name of the image file
     * @return the full path to the blog thumbnail image
     */
    public String getThumbnailImagePath(String filename) {
        return BASE_IMAGE_DIR + File.separator + BLOG_THUMBNAILS_DIR + File.separator + filename;
    }
    
    /**
     * Gets the full path to a blog main image.
     * 
     * @param filename the name of the image file
     * @return the full path to the blog main image
     */
    public String getBlogImagePath(String filename) {
        return BASE_IMAGE_DIR + File.separator + BLOG_IMAGES_DIR + File.separator + filename;
    }
    
    /**
     * Gets the web-accessible URL path to a profile image (for use in HTML).
     * 
     * @param filename the name of the image file
     * @return the web URL path to the profile image
     */
    public String getProfileImageUrl(String filename) {
        return "/resources/images/" + PROFILE_DIR + "/" + filename;
    }
    
    /**
     * Gets the web-accessible URL path to a blog thumbnail (for use in HTML).
     * 
     * @param filename the name of the image file
     * @return the web URL path to the blog thumbnail
     */
    public String getThumbnailImageUrl(String filename) {
        return "/resources/images/" + BLOG_THUMBNAILS_DIR + "/" + filename;
    }
    
    /**
     * Gets the web-accessible URL path to a blog main image (for use in HTML).
     * 
     * @param filename the name of the image file
     * @return the web URL path to the blog main image
     */
    public String getBlogImageUrl(String filename) {
        return "/resources/images/" + BLOG_IMAGES_DIR + "/" + filename;
    }
    
    /**
     * Uploads a profile image.
     * 
     * @param part the {@link Part} object representing the uploaded file
     * @return true if successful, false otherwise
     */
    public boolean uploadProfileImage(Part part) {
        String imageName = getImageNameFromPart(part);
        return uploadImage(part, getProfileImagePath(imageName));
    }
    
    /**
     * Uploads a blog thumbnail image.
     * 
     * @param part the {@link Part} object representing the uploaded file
     * @return the filename of the uploaded image if successful, null otherwise
     */
    public String uploadThumbnailImage(Part part) {
        String imageName = getImageNameFromPart(part);
        boolean success = uploadImage(part, getThumbnailImagePath(imageName));
        return success ? imageName : null;
    }
    
    /**
     * Uploads a blog main image.
     * 
     * @param part the {@link Part} object representing the uploaded file
     * @return the filename of the uploaded image if successful, null otherwise
     */
    public String uploadBlogImage(Part part) {
        String imageName = getImageNameFromPart(part);
        boolean success = uploadImage(part, getBlogImagePath(imageName));
        return success ? imageName : null;
    }
    
    /**
     * Internal method to handle the actual file upload.
     * 
     * @param part the {@link Part} object representing the uploaded file
     * @param destinationPath the full path where the file should be saved
     * @return true if successful, false otherwise
     */
    private boolean uploadImage(Part part, String destinationPath) {
        try {
            // Ensure the directory exists
            File destFile = new File(destinationPath);
            File parentDir = destFile.getParentFile();
            
            if (!parentDir.exists() && !parentDir.mkdirs()) {
                System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
                return false;
            }
            
            // Write the file
            part.write(destinationPath);
            return true;
        } catch (IOException e) {
            System.err.println("Error uploading image: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Deletes a profile image file.
     * 
     * @param filename the name of the image file to delete
     * @return true if successfully deleted, false otherwise
     */
    public boolean deleteProfileImage(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            return false;
        }
        
        try {
            File fileToDelete = new File(getProfileImagePath(filename));
            if (fileToDelete.exists()) {
                return fileToDelete.delete();
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error deleting profile image: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}