package model;

/**
 * Model class representing login credentials for a user.
 * Stores the user's email and password for authentication purposes.
 *
 * @author Aashu Kumar Sah
 */
public class LoginModel {
    // The user's email address
    private String email;
    // The user's password
    private String password;

    /**
     * Default constructor
     */
    public LoginModel() {
    }

    /**
     * Parameterized constructor
     *
     * @param email    The user's email address
     * @param password The user's password
     */
    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
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
     * @param email The user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password The user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}