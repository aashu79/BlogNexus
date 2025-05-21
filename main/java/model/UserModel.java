package model;

public class UserModel {

	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String country;
	private String profilePicture;
	private String password;
	private String userRole; // Using String for simplicity, could use an Enum too

	// Default constructor
	public UserModel() {
	}

	// Parameterized constructor (without userId, often auto-generated)
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

	// Parameterized constructor (including userId)
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
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getUserRole() {
		return userRole;
	}

	public String getPassword(String password) {
		return this.password;
	}

	public void setUserRole(String userRole) {
		// Optional: Add validation to ensure role is 'admin' or 'general'
		this.userRole = userRole;
	}

	// Add this field to your UserModel class
	private String profilePictureUrl;

	// And add these getter/setter methods
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	// Optional: toString method for debugging/logging
	@Override
	public String toString() {
		return "UserModel{" + "userId=" + userId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", country='" + country + '\''
				+ ", profilePicture='" + profilePicture + '\'' + ", userRole='" + userRole + '\'' + '}';
	}
}