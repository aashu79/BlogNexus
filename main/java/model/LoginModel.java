package model;

public class LoginModel {
    private String email;
    private String password;

    // Default constructor
    public LoginModel() {
    }

    // Parameterized constructor
    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}