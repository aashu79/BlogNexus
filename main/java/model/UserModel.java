package model;

/**
 * Model class representing a user of the system.
 * Stores user details such as ID, name, contact info, role, and profile picture.
 *
 * @author Aashu Kumar Sah
 */
public class UserModel {

    // Unique identifier for the user
    private int userId;
    // User's first name
    private String firstName;
    // User's last name
    private String lastName;
    // User's email address
    private String email;
    // User's phone number
    private String phone;
    // User's country
    private String country;
    // File name or path of the user's profile picture
    private String profilePicture;
    // User's password (should be stored securely in production)
    private String password;
    // Role of the user (e.g., "admin", "general"); could use Enum for stronger typing
    private String userRole;

    // URL to the user's profile picture (for external usage)
    private String profilePictureUrl;

    /**
     * Default constructor
     */
    public UserModel() {
    }

    /**
     * Parameterized constructor (without userId, often auto-generated)
     *
     * @param firstName       User's first name
     * @param lastName        User's last name
     * @param email           User's email address
     * @param phone           User's phone number
     * @param country         User's country
     * @param profilePicture  File name or path of the profile picture
     * @param userRole        User's role (admin/general)
     * @param password        User's password
     */
    public UserModel(String firstName, String lastName, String email, String phone, String country,
                     String profilePicture, String userRole, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.profilePicture = profilePicture;
        this.userRole = userRole;
    }

    /**
     * Parameterized constructor (including userId)
     *
     * @param userId          Unique identifier for the user
     * @param firstName       User's first name
     * @param lastName        User's last name
     * @param email           User's email address
     * @param phone           User's phone number
     * @param country         User's country
     * @param profilePicture  File name or path of the profile picture
     * @param userRole        User's role (admin/general)
     * @param password        User's password
     */
    public UserModel(int userId, String firstName, String lastName, String email, String phone, String country,
                     String profilePicture, String userRole, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.password = password;
        this.profilePicture = profilePicture;
        this.userRole = userRole;
    }

    // Getters and Setters

    /**
     * Gets the user ID.
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * @param userId Unique identifier for the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's first name.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     * @param firstName User's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     * @param lastName User's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's email address.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email User's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the user's password.
     * @param password User's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's phone number.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     * @param phone User's phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the user's country.
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the user's country.
     * @param country User's country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the user's profile picture file name or path.
     * @return profilePicture
     */
    public String getProfilePicture() {
        return profilePicture;
    }

    /**
     * Sets the user's profile picture file name or path.
     * @param profilePicture File name or path of the profile picture
     */
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * Gets the user's role.
     * @return userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Gets the user's password.
     * @param password (unused)
     * @return password
     */
    public String getPassword(String password) {
        return this.password;
    }

    /**
     * Sets the user's role.
     * @param userRole User's role (e.g., "admin", "general")
     */
    public void setUserRole(String userRole) {
        // Optional: Add validation to ensure role is 'admin' or 'general'
        this.userRole = userRole;
    }

    /**
     * Gets the URL to the user's profile picture.
     * @return profilePictureUrl
     */
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    /**
     * Sets the URL to the user's profile picture.
     * @param profilePictureUrl URL to the profile picture
     */
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    /**
     * Returns a string representation of the UserModel for debugging/logging.
     * @return String representation of UserModel
     */
    @Override
    public String toString() {
        return "UserModel{" + "userId=" + userId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", country='" + country + '\''
                + ", profilePicture='" + profilePicture + '\'' + ", userRole='" + userRole + '\'' + '}';
    }
}